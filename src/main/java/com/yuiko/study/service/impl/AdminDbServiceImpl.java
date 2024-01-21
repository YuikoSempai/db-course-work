package com.yuiko.study.service.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import com.yuiko.study.model.Environment;
import com.yuiko.study.model.enums.Type;
import com.yuiko.study.service.AdminDbService;
import com.yuiko.study.util.DbMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdminDbServiceImpl implements AdminDbService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AdminDbServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addNewEnvironment(Environment environment) {
        String sql = """
                insert into best_env (flower_species, soil, fertilizer_type, water_type, def_soil, def_fer, def_water)
                VALUES (:flowerSpecies, cast(:soil as resources_type), :fertilizerType, :waterType, :defSoil, :defFer, :defWater)
                on conflict (flower_species) do update set
                soil = excluded.soil,
                fertilizer_type = excluded.fertilizer_type,
                water_type = excluded.water_type
                """;

        return jdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("flowerSpecies", environment.flowerSpecies(), Types.OTHER)
                        .addValue("soil", environment.soil(), Types.OTHER)
                        .addValue("fertilizerType", environment.fertilizerType(), Types.OTHER)
                        .addValue("waterType", environment.waterType(), Types.OTHER)
                        .addValue("defSoil", 1)
                        .addValue("defFer", 20)
                        .addValue("defWater", 5)
        ) != 0;
    }

    @Override
    public List<Environment> getAllEnvironments() {
        String sql = "select * from best_env";

        return jdbcTemplate.query(sql, Map.of(), DbMapper.ENVIRONMENT_ROW_MAPPER);
    }
}
