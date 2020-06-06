package com.distributionsystem.service;

import com.distributionsystem.dto.UserRegistrationDto;
import com.distributionsystem.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto userRegistrationDto);
}
