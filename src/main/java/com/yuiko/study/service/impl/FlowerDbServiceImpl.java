package com.yuiko.study.service.impl;

import java.util.List;

import com.yuiko.study.api.response.ResponseWithMessage;
import com.yuiko.study.model.Flower;
import com.yuiko.study.service.FlowerDbService;
import com.yuiko.study.util.DbMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FlowerDbServiceImpl implements FlowerDbService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FlowerDbServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Flower> getFlowersByUserId(long uid) {
        String sql = """
                select * from user_flowers
                where user_id = :user_id;
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", uid);

        return jdbcTemplate.query(
                sql,
                params,
                DbMapper.FLOWER_ROW_MAPPER
        );
    }

    @Override
    public ResponseWithMessage addFlower(long uid, Flower flower) {
        String sql = """
                insert into user_flowers (user_id, flower_species, soil, fertilizer_type, water_type, height)
                values (:user_id, :flower_species, :soil, :fertilizer_type, :water_type, :height)
                """;

        return wrapAnswer(jdbcTemplate.update(sql, getFlowerParams(uid, flower)));
    }

    @Override
    public ResponseWithMessage updateFLower(long uid, Flower flower) {
        String sql = """
                update user_flowers
                set flower_species = :flower_species,
                    soil = :soil,
                    fertilizer_type = :fertilizer_type,
                    water_type = :water_type,
                    height = :height
                where id = :id and user_id = :user_id
                """;

        return wrapAnswer(jdbcTemplate.update(sql, getFlowerParams(uid, flower)));
    }

    @Override
    public ResponseWithMessage deleteFlower(long uid, long flowerId) {
        String sql = """
                delete from user_flowers
                where id = :id and user_id = :user_id
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", uid);
        params.addValue("id", flowerId);

        return wrapAnswer(jdbcTemplate.update(sql, params));
    }

    private ResponseWithMessage wrapAnswer(long countOfRows) {
        if (countOfRows != 0) {
            return new ResponseWithMessage(200, "Action was performed");
        }
        return new ResponseWithMessage(400, "Action wasn't perform, some problems occurred");
    }

    private MapSqlParameterSource getFlowerParams(long uid, Flower flower) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", uid);
        params.addValue("flower_species", flower.getFlowerSpecies());
        params.addValue("soil", flower.getSoil());
        params.addValue("fertilizer_type", flower.getFertilizerType());
        params.addValue("water_type", flower.getWaterType());
        params.addValue("height", flower.getHeight());
        params.addValue("id", flower.getId());
        return params;
    }
}
