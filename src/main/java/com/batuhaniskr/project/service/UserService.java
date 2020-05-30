package com.batuhaniskr.project.service;

import com.batuhaniskr.project.dto.UserRegistrationDto;
import com.batuhaniskr.project.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto userRegistrationDto);
}
