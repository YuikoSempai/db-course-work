package com.yuiko.study.service.impl;

import java.sql.Types;

import com.yuiko.study.api.response.ResourcesPageDto;
import com.yuiko.study.api.response.UserResources;
import com.yuiko.study.service.ResourcesService;
import com.yuiko.study.util.DbMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ResourcesDbService implements ResourcesService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ResourcesDbService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResourcesPageDto getResourcesByUserId(long uid) {
        String sql = """
                select * from user_resources
                where user_id = :uid
                """;

        return new ResourcesPageDto(jdbcTemplate.query(
                sql,
                new MapSqlParameterSource("uid", uid),
                DbMapper.USER_RESOURCES_ROW_MAPPER
        ));
    }

    @Override
    public boolean addResourcesForUser(long uid, UserResources userResources) {
        String sql = """
                insert into user_resources (user_id, type, object_type, volume)
                VALUES (:uid, :type, :object_type, :volume)
                """;

        return jdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("uid", uid)
                        .addValue("type", userResources.type(), Types.OTHER)
                        .addValue("object_type", userResources.resourceType(), Types.OTHER)
                        .addValue("volume", userResources.amount())
        ) > 0;
    }

    @Override
    public ResourcesPageDto getBestEnv(long uid) {
        String sql = """
                with need_res as (select max(user_id) as user_id, resource as resources_type, sum(val) as sum_val, type
                                  from (select user_id, be.fertilizer_type as resource, def_fer as val, 'fertilizer' as type
                                        from user_flowers uf
                                                 join best_env be on be.flower_species = uf.flower_species
                                        where be.fertilizer_type != uf.fertilizer_type
                                          and uf.user_id = :uid
                                        union all
                                        select user_id, be.soil, def_soil, 'soil'
                                        from user_flowers uf
                                                 join best_env be on be.flower_species = uf.flower_species
                                        where be.soil != uf.soil
                                          and uf.user_id = :uid
                                        union all
                                        select user_id, be.water_type, def_water, 'water'
                                        from user_flowers uf
                                                 join best_env be on be.flower_species = uf.flower_species
                                        where be.water_type != uf.water_type
                                          and uf.user_id = :uid) as types
                                  group by resource, type)
                select nr.user_id, nr.type, resources_type, case when ur.volume is null then nr.sum_val else nr.sum_val - ur.volume end
                from need_res nr
                         left join user_resources as ur on nr.resources_type = ur.type
                where ur.volume is null or nr.sum_val > ur.volume
                """;

        return new ResourcesPageDto(jdbcTemplate.query(
                sql,
                new MapSqlParameterSource("uid", uid),
                DbMapper.USER_RESOURCES_ROW_MAPPER
        ));
    }
}
