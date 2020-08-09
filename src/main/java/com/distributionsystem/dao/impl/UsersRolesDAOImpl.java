package com.distributionsystem.dao.impl;

import com.distributionsystem.dao.UsersRolesDAO;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UsersRolesDAOImpl extends NamedParameterJdbcDaoSupport implements UsersRolesDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRolesDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
        this.jdbcTemplate = getNamedParameterJdbcTemplate();
    }

    @Override
    public void save(long userId, long roleId) {
        String sql = "INSERT INTO PUBLIC.USERS_ROLES (USER_ID, ROLE_ID) VALUES (:userId, :roleId)";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("userId", userId);
        paramSource.addValue("roleId", roleId);
        jdbcTemplate.update(sql, paramSource);
    }

}