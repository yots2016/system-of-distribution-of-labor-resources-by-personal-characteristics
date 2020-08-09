package com.distributionsystem.service.impl;

import com.distributionsystem.dao.*;
import com.distributionsystem.dto.UserRegistrationDto;
import com.distributionsystem.model.Role;
import com.distributionsystem.model.User;
import com.distributionsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final UsersRolesDAO usersRolesDAO;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userDAO.findByEmail(email)
                .map(this::convertToSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
    }

    private org.springframework.security.core.userdetails.User convertToSecurityUser(User user) {
        Long userId = user.getId();
        Collection<Role> roles = roleDAO.findAllByUserId(userId);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(roles));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public void save(UserRegistrationDto registration) {
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));

        OptionalLong userId = userDAO.save(user);
        OptionalLong roleId = roleDAO.save(ROLE_USER);
        usersRolesDAO.save(userId.orElseThrow(() -> new RuntimeException("User ID must be inserted previously")),
                roleId.orElseThrow(() -> new RuntimeException("Role ID must be inserted previously")));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}