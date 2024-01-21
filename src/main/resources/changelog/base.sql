CREATE TYPE diseases_state AS ENUM ('started', 'continuation', 'advanced');
CREATE TYPE diseases_type AS ENUM ('fungal', 'viral', 'bacterial', 'nematodes', 'physiological');
CREATE TYPE flower_species AS ENUM ('rose', 'tulip', 'sunflower', 'lavender', 'oak_tree', 'daisy', 'maple_tree', 'cactus', 'fern', 'daffodil');
CREATE TYPE resources_type AS ENUM ('organic', 'potassium_nitrate', 'nitroammophoska', 'nitrophoska', 'ammophos', 'sandy', 'clayey', 'peat', 'podzolic', 'crane', 'rainy', 'bottled', 'salty', 'mineral');
CREATE TYPE object_type AS ENUM ('water', 'fertilizer', 'soil');
CREATE TYPE light_type AS ENUM ('solar', 'phytolamp', 'led');

CREATE TABLE if not exists user_flowers
(
    id              SERIAL PRIMARY KEY,
    user_id         bigint,
    flower_species  flower_species,
    soil            resources_type,
    fertilizer_type resources_type,
    water_type      resources_type,
    height          REAL
);

create sequence users_seq;
create table users
(
    id       int default nextval('users_seq') primary key,
    username text,
    password text,
    unique (username, password)
);

insert into user_flowers (user_id, flower_species, soil, fertilizer_type, water_type, height)
values (100, 'rose', 'podzolic', 'organic', 'crane', 10),
       (100, 'tulip', 'podzolic', 'organic', 'crane', 10);

CREATE TABLE user_resources
(
    user_id     int,
    type        resources_type PRIMARY KEY,
    object_type object_type,
    volume      int
);

INSERT INTO user_resources (user_id, type, object_type, volume)
VALUES (100, 'organic', 'fertilizer', 0),
       (100, 'potassium_nitrate', 'fertilizer', 0),
       (100, 'nitroammophoska', 'fertilizer', 0),
       (100, 'nitrophoska', 'fertilizer', 0),
       (100, 'ammophos', 'fertilizer', 0),
       (100, 'sandy', 'soil', 0),
       (100, 'clayey', 'soil', 0),
       (100, 'peat', 'soil', 0),
       (100, 'podzolic','soil', 0),
       (100, 'crane', 'water', 0),
       (100, 'rainy', 'water', 0),
       (100, 'bottled', 'water', 0),
       (100, 'salty', 'water', 0),
       (100, 'mineral', 'water', 0);

create table best_env
(
    id              serial primary key,
    flower_species  flower_species,
    soil            resources_type,
    fertilizer_type resources_type,
    water_type      resources_type,
    def_soil        int,
    def_fer         int,
    def_water       int,
    unique (flower_species)
);

insert into best_env (flower_species, soil, fertilizer_type, water_type, def_soil, def_fer, def_water)
VALUES ('rose', 'sandy', 'organic', 'bottled', 1, 2, 3),
       ('tulip', 'sandy', 'organic', 'bottled', 10, 20, 30);

CREATE TABLE user_diseases
(
    id             SERIAL PRIMARY KEY,
    flower_id      int references user_flowers (id) on delete cascade,
    diseases_state diseases_state,
    diseases_type  diseases_type
);

insert into user_diseases (flower_id, diseases_state, diseases_type)
VALUES (1, 'started', 'viral'),
       (2, 'continuation', 'fungal');

create table water_schedule
(
    user_id       int,
    flower_id     int references user_flowers (id) on delete cascade on update set default primary key default 1,
    watering_time timestamp with time zone
);

insert into water_schedule (user_id, flower_id, watering_time)
VALUES (100, 1, now() - interval '2' day),
       (100, 2, now() - interval '10' day)
on conflict (flower_id) do update set watering_time = excluded.watering_time;