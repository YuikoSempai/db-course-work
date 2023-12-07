package com.yuiko.study.util;


import com.yuiko.study.model.Flower;
import org.springframework.jdbc.core.RowMapper;

public class DbMapper {

    public static final RowMapper<Flower> FLOWER_ROW_MAPPER = (rs, idx) -> Flower.builder()
            .id(rs.getLong("id"))
            .userId(rs.getLong("user_id"))
            .flowerSpecies(rs.getString("flower_species"))
            .soil(rs.getString("soil"))
            .fertilizerType(rs.getString("fertilizer_type"))
            .waterType(rs.getString("water_type"))
            .height(rs.getDouble("height"))
            .build();
}