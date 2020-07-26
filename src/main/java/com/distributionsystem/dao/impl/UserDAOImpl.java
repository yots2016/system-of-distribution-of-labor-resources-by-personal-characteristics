package com.distributionsystem.dao.impl;

import com.distributionsystem.dao.UserDAO;
import com.distributionsystem.model.User;
import com.distributionsystem.rowmapper.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
        this.jdbcTemplate = getNamedParameterJdbcTemplate();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM PUBLIC.USER WHERE EMAIL=:email";
        MapSqlParameterSource paramSource = new MapSqlParameterSource("email", email);
        try {
            User user = jdbcTemplate.queryForObject(sql, paramSource, new UserRowMapper());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}