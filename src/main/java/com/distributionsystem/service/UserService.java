package com.distributionsystem.service;

import com.distributionsystem.dto.UserRegistrationDto;
import com.distributionsystem.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserService extends UserDetailsService {

    Optional<User> findByEmail(String email);

    void save(UserRegistrationDto userRegistrationDto);

}
