package com.yuiko.study.service.impl;

import java.util.List;

import com.yuiko.study.api.response.ResponseWithMessage;
import com.yuiko.study.api.response.Statistic;
import com.yuiko.study.model.CheckedFlower;
import com.yuiko.study.model.Flower;
import com.yuiko.study.service.FlowerDbService;
import com.yuiko.study.util.DbMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class FlowerDbServiceImpl implements FlowerDbService {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public FlowerDbServiceImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public List<Flower> getFlowersByUserId(long uid) {
        String sql = """
                select * from user_flowers
                where user_id = :user_id;
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", uid);

        return namedJdbcTemplate.query(
                sql,
                params,
                DbMapper.FLOWER_ROW_MAPPER
        );
    }

    @Override
    public boolean addFlower(long uid, Flower flower) {
        String sql = """
                insert into user_flowers (user_id, flower_species, soil, fertilizer_type, water_type, height)
                values (:user_id, :flower_species, :soil, :fertilizer_type, :water_type, :height)
                """;

        return namedJdbcTemplate.update(sql, getFlowerParams(uid, flower)) != 0;
    }

    @Override
    public boolean updateFLower(long uid, Flower flower) {
        String sql = """
                update user_flowers
                set flower_species = :flower_species,
                    soil = :soil,
                    fertilizer_type = :fertilizer_type,
                    water_type = :water_type,
                    height = :height
                where id = :id and user_id = :user_id
                """;

        return namedJdbcTemplate.update(sql, getFlowerParams(uid, flower)) != 0;
    }

    @Override
    public boolean deleteFlower(long uid, long flowerId) {
        String sql = """
                delete from user_flowers
                where id = :id and user_id = :user_id
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", uid);
        params.addValue("id", flowerId);

        return namedJdbcTemplate.update(sql, params) != 0;
    }

    @Override
    public Statistic getStatistic(long uid) {
        return null;
    }

    @Override
    public List<CheckedFlower> checkFlowerEnv(long uid) {
        String sql = """
                """;

        return null;
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
