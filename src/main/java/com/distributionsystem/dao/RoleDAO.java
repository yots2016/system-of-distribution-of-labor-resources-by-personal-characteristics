package com.distributionsystem.dao;

import com.distributionsystem.model.Role;

import java.util.Collection;
import java.util.OptionalLong;

public interface RoleDAO {

    Collection<Role> findAllByUserId(Long id);

    OptionalLong save(String role);

}