package com.yuiko.study.util;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.TemporalUnit;

import com.yuiko.study.api.response.UserResources;
import com.yuiko.study.model.CheckedFlower;
import com.yuiko.study.model.Environment;
import com.yuiko.study.model.Flower;
import com.yuiko.study.model.User;
import com.yuiko.study.model.Watering;
import com.yuiko.study.model.enums.FertilizerType;
import com.yuiko.study.model.enums.FlowerSpecies;
import com.yuiko.study.model.enums.ResourcesType;
import com.yuiko.study.model.enums.SoilType;
import com.yuiko.study.model.enums.Type;
import com.yuiko.study.model.enums.WaterType;
import org.springframework.jdbc.core.RowMapper;

public class DbMapper {

    public static final RowMapper<Flower> FLOWER_ROW_MAPPER = (rs, idx) -> Flower.builder()
            .id(rs.getLong("id"))
            .userId(rs.getLong("user_id"))
            .flowerSpecies(FlowerSpecies.valueOf(rs.getString("flower_species")))
            .soil(SoilType.valueOf(rs.getString("soil")))
            .fertilizerType(FertilizerType.valueOf(rs.getString("fertilizer_type")))
            .waterType(WaterType.valueOf(rs.getString("water_type")))
            .height(rs.getDouble("height"))
            .build();

    public static final RowMapper<User> USER_ROW_MAPPER = (rs, idx) -> User.builder()
            .id(rs.getLong("id"))
            .username(rs.getString("username"))
            .password(rs.getString("password"))
            .build();

    public static final RowMapper<UserResources> USER_RESOURCES_ROW_MAPPER = (rs, idx) -> new UserResources(
            rs.getLong("user_id"),
            rs.getDouble("volume"),
            Type.valueOf(rs.getString("object_type")),
            ResourcesType.valueOf(rs.getString("type"))
    );

    public static final RowMapper<CheckedFlower> CHECKED_FLOWER_ROW_MAPPER = (rs, idx) -> new CheckedFlower(
            rs.getLong("id"),
            rs.getLong("user_id"),
            FlowerSpecies.valueOf(rs.getString("flower_species")),
            rs.getBoolean("soil"),
            rs.getBoolean("fertilizer_type"),
            rs.getBoolean("water_type")
    );

    public static final RowMapper<Watering> WATERING_ROW_MAPPER = (rs, idx) -> new Watering(
            rs.getLong("flower_id"),
            rs.getLong("user_id"),
            Timestamp.from(Instant.now().minusSeconds(86400)).after(rs.getTimestamp("watering_time")),
            rs.getTimestamp("watering_time").toString()
    );

    public static final RowMapper<Environment> ENVIRONMENT_ROW_MAPPER = (rs, idx) -> new Environment(
            FlowerSpecies.valueOf(rs.getString("flower_species")),
            SoilType.valueOf(rs.getString("soil")),
            FertilizerType.valueOf(rs.getString("fertilizer_type")),
            WaterType.valueOf(rs.getString("water_type"))
    );
}
