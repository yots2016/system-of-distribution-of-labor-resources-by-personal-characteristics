package com.distributionsystem.dao;

import com.distributionsystem.model.User;

import java.util.Optional;
import java.util.OptionalLong;

public interface UserDAO {

    Optional<User> findByEmail(String email);

    OptionalLong save(User user);

}