package com.distributionsystem.rowmapper;

import com.distributionsystem.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        var role = new Role();
        role.setId(rs.getLong(1));
        role.setName(rs.getString(2));
        return role;
    }

}