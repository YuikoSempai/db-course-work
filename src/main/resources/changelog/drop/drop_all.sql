drop function if exists check_user_resources_insert_condition();

drop function if exists  check_user_resources_volume_range();

drop function if exists  get_average_light_intensity();

drop function if exists  check_watering_schedule(integer, date);

drop function if exists  check_and_update_watering_schedule();

drop procedure if exists  add_new_plant(flower_species, integer, integer, integer, integer, integer);

drop procedure if exists  add_new_plant(flower_species, resources_type, real);

drop procedure if exists  delete_plant_by_id(integer);

drop procedure if exists  update_plant_by_id(integer, flower_species, integer, integer, integer, integer, integer);

drop procedure if exists  update_user_plant_by_id(integer, flower_species, resources_type, real);

drop function if exists  check_soil_type(integer);

drop function if exists  check_water_type(integer);

drop function if exists  check_light_type(integer);

drop table if exists user_changelog, user_volume;

DROP TABLE IF EXISTS user_flowers, user_resources, user_schedule, user_diseases, user_light_schedule CASCADE;
DROP TYPE IF EXISTS light_type, object_type, resources_type, flower_species, diseases_type, diseases_state, type_fertilizers;

DROP TABLE IF EXISTS
    flowers,
    pests_diseases,
    flowers_diseases,
    flowers_pests,
    pests,
    diseases,
    fertilizers,
    watering_info,
    water_info,
    light_info,
    soils,
    climate_type,
    scheduler,
    volume
    CASCADE;

DROP TYPE IF EXISTS
    quality,
    type_light,
    type_diseases,
    water_type,
    fertilizer_type,
    climate_types,
    soils_type;

