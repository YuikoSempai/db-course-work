package com.yuiko.study.service.impl;

import java.util.Optional;

import com.yuiko.study.model.User;
import com.yuiko.study.service.UserDbService;
import com.yuiko.study.util.DbMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Service;

@Service
public class UserDbServiceImpl implements UserDbService {

    private final JdbcTemplate jdbcTemplate;

    public UserDbServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long addUser(User user) {
        String sql = """
                insert into users (username, password)
                values (?, ?)
                returning id
                """;

        return jdbcTemplate.query(
                sql,
                new SingleColumnRowMapper<>(Long.class),
                user.getUsername(),
                user.getPassword()
        ).stream().findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public Optional<User> findUser(User user) {
        String sql = """
                select *
                from users
                where username = ? and password = ?
                """;
        return jdbcTemplate.query(
                sql,
                DbMapper.USER_ROW_MAPPER,
                user.getUsername(),
                user.getPassword()
        ).stream().findFirst();
    }
}
