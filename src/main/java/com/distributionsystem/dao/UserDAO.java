package com.distributionsystem.dao;

import com.distributionsystem.model.User;

import java.util.Optional;

public interface UserDAO {

    Optional<User> findByEmail(String email);

}