package com.distributionsystem.dao.impl;

import com.distributionsystem.dao.RoleDAO;
import com.distributionsystem.model.Role;
import com.distributionsystem.rowmapper.RoleRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class RoleDAOImpl extends NamedParameterJdbcDaoSupport implements RoleDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public RoleDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
        this.namedParameterJdbcTemplate = getNamedParameterJdbcTemplate();
        this.jdbcTemplate = getJdbcTemplate();
    }

    @Override
    public Collection<Role> findAllByUserId(Long userId) {
        var sql = "SELECT R.ID, R.NAME "
                + "FROM PUBLIC.USERS_ROLES "
                + "INNER JOIN PUBLIC.USER U ON U.ID = USERS_ROLES.USER_ID "
                + "INNER JOIN PUBLIC.ROLE R ON R.ID = USERS_ROLES.ROLE_ID "
                + "WHERE U.ID = :userId";
        var parameterSource = new MapSqlParameterSource("userId", userId);
        return namedParameterJdbcTemplate.query(sql, parameterSource, new RoleRowMapper());
    }

    @Override
    public OptionalLong save(String role) {
        var paramSource = new MapSqlParameterSource("name", role);
        var simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate)
                .withSchemaName("PUBLIC")
                .withTableName("ROLE")
                .usingColumns("NAME")
                .usingGeneratedKeyColumns("id");
        var keyHolder = simpleJdbcInsert.executeAndReturnKeyHolder(paramSource);
        var key = keyHolder.getKey();
        return Optional.ofNullable(key)
                .map(keyValue -> OptionalLong.of(keyValue.longValue()))
                .orElse(OptionalLong.empty());
    }

}