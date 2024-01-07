package com.yuiko.study.util;


import com.yuiko.study.api.response.UserResources;
import com.yuiko.study.model.Flower;
import com.yuiko.study.model.User;
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
            rs.getLong("userId"),
            rs.getDouble("amount"),
            Type.valueOf(rs.getString("type")),
            ResourcesType.valueOf(rs.getString("resources_type"))
    );
}
