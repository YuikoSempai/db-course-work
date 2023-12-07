package com.yuiko.study.service.impl;

import java.util.List;

import com.yuiko.study.model.Flower;
import com.yuiko.study.service.FlowerService;
import com.yuiko.study.util.DbMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FlowerServiceImpl implements FlowerService {

    private final JdbcTemplate jdbcTemplate;

    public FlowerServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Flower> getFlowersByUserId(Long uid) {
        String sql = """
                select * from user_flowers;
                """;

        return jdbcTemplate.query(
                sql,
                DbMapper.FLOWER_ROW_MAPPER
        );
    }
}
