package com.distributionsystem.dao.impl;

import com.distributionsystem.dao.UserDAO;
import com.distributionsystem.model.User;
import com.distributionsystem.rowmapper.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.OptionalLong;

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

    @Override
    public OptionalLong save(User user) {
        String sql = "INSERT INTO PUBLIC.USER (EMAIL, USERNAME, PASSWORD) VALUES (:email, :username, :password)";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("email", user.getEmail());
        paramSource.addValue("username", user.getUsername());
        paramSource.addValue("password", user.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, paramSource, keyHolder, new String[]{"id"});
        Number key = keyHolder.getKey();
        return Optional.ofNullable(key)
                .map(keyValue -> OptionalLong.of(keyValue.longValue()))
                .orElse(OptionalLong.empty());
    }

}