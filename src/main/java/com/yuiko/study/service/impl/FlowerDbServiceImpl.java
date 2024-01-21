package com.yuiko.study.service.impl;

import java.sql.Types;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.yuiko.study.api.response.Statistic;
import com.yuiko.study.model.CheckedFlower;
import com.yuiko.study.model.Flower;
import com.yuiko.study.model.FlowersBySpeciesCount;
import com.yuiko.study.model.Watering;
import com.yuiko.study.model.enums.FlowerSpecies;
import com.yuiko.study.service.FlowerDbService;
import com.yuiko.study.util.DbMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
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
        ).stream().sorted(Comparator.comparing(Flower::getId)).toList();
    }

    @Override
    public boolean addFlower(long uid, Flower flower) {
        String addFlower = """
                insert into user_flowers (id, user_id, flower_species, soil, fertilizer_type, water_type, height)
                values (:id, :user_id, :flower_species, :soil, :fertilizer_type, :water_type, :height)
                on conflict (id) do update set
                flower_species = excluded.flower_species,
                fertilizer_type = excluded.fertilizer_type,
                water_type = excluded.water_type,
                height = excluded.height
                returning id
                """;

        String addWaterSchedule = """
                insert into water_schedule (user_id, flower_id, watering_time) 
                values (:user_id, :flower_id, now())
                """;

        MapSqlParameterSource flowerParams = getFlowerParams(uid, flower);

        if (flower.getId() != 0L) {
            flowerParams.addValue("id", flower.getId());
            namedJdbcTemplate.query(
                    addFlower,
                    flowerParams,
                    SingleColumnRowMapper.newInstance(Long.class)
            ).stream().findFirst().orElseThrow();
            return true;
        }

        long flowerId = namedJdbcTemplate.query(
                addFlower,
                flowerParams,
                SingleColumnRowMapper.newInstance(Long.class)
        ).stream().findFirst().orElseThrow();

        return namedJdbcTemplate.update(
                addWaterSchedule,
                new MapSqlParameterSource()
                        .addValue("user_id", uid)
                        .addValue("flower_id", flowerId)
        ) != 0;
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
                where id = :flowerId and user_id = :userId
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", uid);
        params.addValue("flowerId", flowerId);

        namedJdbcTemplate.update(sql, params);
//        setIdx(flowerId);
        return true;
    }

    @Override
    public Statistic getStatistic() {
        String sql = """
                with simple_stat as (
                    select avg(uf.height) as avg_height, count(*) as flowers_count
                    from user_flowers uf
                ), best_env as (
                    select count(*) as flowers_best_env
                from user_flowers uf
                         left join best_env be on be.flower_species = uf.flower_species
                where be.water_type = uf.water_type and be.soil = uf.soil and be.fertilizer_type = uf.fertilizer_type
                )
                select * from simple_stat, best_env
                """;

        List<FlowersBySpeciesCount> countBySpecies = namedJdbcTemplate.query(
                """
                        select flower_species, count(flower_species)
                        from user_flowers
                        group by flower_species
                        """,
                Map.of(),
                (rs, idx) -> new FlowersBySpeciesCount(
                        FlowerSpecies.valueOf(rs.getString("flower_species")),
                        rs.getLong("count")
                )
        );

        return namedJdbcTemplate.query(
                sql,
                Map.of(),
                (rs, idx) -> new Statistic(
                        rs.getDouble("avg_height"),
                        rs.getLong("flowers_count"),
                        countBySpecies,
                        "empty",
                        0L,
                        rs.getLong("flowers_best_env")
                )
        ).stream().findFirst().orElseThrow();
    }

    @Override
    public List<CheckedFlower> checkFlowerEnv(long uid) {
        String sql = """
                select uf.id as id,
                       uf.user_id as user_id,
                       uf.flower_species as flower_species,
                       case
                           when be.soil is not null
                               then uf.soil = be.soil
                           else true end as soil,
                       case
                           when be.fertilizer_type is not null
                               then uf.fertilizer_type = be.fertilizer_type
                           else true end as fertilizer_type,
                       case
                           when be.water_type is not null
                               then uf.water_type = be.water_type
                           else true end as water_type
                from user_flowers uf
                         left join best_env be on be.flower_species = uf.flower_species
                where user_id = :uid
                """;

        return namedJdbcTemplate.query(
                sql,
                new MapSqlParameterSource("uid", uid),
                DbMapper.CHECKED_FLOWER_ROW_MAPPER
        );
    }

    @Override
    public boolean waterFlower(long uid, long flowerId) {
        String sql = """
                insert into water_schedule (user_id, flower_id, watering_time)
                VALUES (:uid, :flowerId, now())
                on conflict (flower_id) do update set
                watering_time = excluded.watering_time;
                """;
        return namedJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("uid", uid)
                        .addValue("flowerId", flowerId)
        ) > 0;
    }

    @Override
    public List<Watering> getWateringSchedule(long uid) {
        String sql = """
                select * from water_schedule
                where user_id = :uid
                """;

        return namedJdbcTemplate.query(sql, new MapSqlParameterSource("uid", uid), DbMapper.WATERING_ROW_MAPPER);
    }

    private MapSqlParameterSource getFlowerParams(long uid, Flower flower) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", uid);
        params.addValue("flower_species", flower.getFlowerSpecies(), Types.OTHER);
        params.addValue("soil", flower.getSoil(), Types.OTHER);
        params.addValue("fertilizer_type", flower.getFertilizerType(), Types.OTHER);
        params.addValue("water_type", flower.getWaterType(), Types.OTHER);
        params.addValue("height", flower.getHeight());
        params.addValue("id", getIdx());
        return params;
    }

    private Long getIdx() {
        Long dbSize = namedJdbcTemplate.query(
                "select count(*) from user_flowers",
                Map.of(),
                SingleColumnRowMapper.newInstance(Long.class)
        ).stream().findFirst().orElseThrow();

        Long lowestIdx = namedJdbcTemplate.query(
                "select min(id) from user_flowers",
                Map.of(),
                SingleColumnRowMapper.newInstance(Long.class)
        ).stream().findFirst().orElseThrow();
        return lowestIdx != 1 ? lowestIdx - 1 : dbSize + 1;
    }

    private void setIdx(long removedIdx) {
        String sql1 = """
                with maxId as (
                    select max(id) as maxId from user_flowers
                )
                update user_flowers set id = :removedIdx from maxId
                where id = maxId.maxId;
                """;
        String sql2 = """
                with maxId as (
                    select max(id) as maxId from user_flowers
                )
                update water_schedule set flower_id = :removedIdx from maxId
                where flower_id = maxId.maxId;
                """;
        namedJdbcTemplate.update(sql1, new MapSqlParameterSource("removedIdx", removedIdx));
        namedJdbcTemplate.update(sql2, new MapSqlParameterSource("removedIdx", removedIdx));
    }
}
