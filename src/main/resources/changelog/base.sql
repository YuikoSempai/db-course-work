CREATE TYPE diseases_state AS ENUM ('started', 'continuation', 'advanced');
CREATE TYPE diseases_type AS ENUM ('fungal', 'viral', 'bacterial', 'nematodes', 'physiological');
CREATE TYPE flower_species AS ENUM ('rose', 'tulip', 'sunflower', 'lavender', 'oak_tree', 'daisy', 'maple_tree', 'cactus', 'fern', 'daffodil');
CREATE TYPE resources_type AS ENUM ('sandy', 'loam', 'clay', 'peat_bogs', 'podzolic', 'crane', 'rainy', 'borehole', 'bottled', 'salty', 'organic', 'mineral', 'organo_mineral', 'biological', 'leafy');
CREATE TYPE object_type AS ENUM ('water', 'fertilizers', 'soil');
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
values (1, 'rose', 'podzolic', 'organic', 'crane', 10),
       (2, 'tulip', 'podzolic', 'organic', 'crane', 10);

CREATE TABLE user_resources
(
    user_id     int,
    type        resources_type PRIMARY KEY,
    object_type object_type,
    volume      int
);

INSERT INTO user_resources (type, object_type, volume)
VALUES ('sandy', 'soil', 300),
       ('loams', 'soil', 320),
       ('clayey', 'soil', 350),
       ('peat_bogs', 'soil', 400),
       ('podzolic', 'soil', 330),
       ('crane', 'water', 200),
       ('rainy', 'water', 220),
       ('borehole', 'water', 250),
       ('bottled', 'water', 180),
       ('salty', 'water', 210),
       ('organic', 'fertilizers', 5),
       ('mineral', 'fertilizers', 5),
       ('organo_mineral', 'fertilizers', 7),
       ('biological', 'fertilizers', 6),
       ('leafy', 'fertilizers', 10);

create table best_env
(
    id              serial primary key,
    flower_species  flower_species,
    soil            resources_type,
    fertilizer_type resources_type,
    water_type      resources_type,
    def_soil        int,
    def_fer         int,
    def_water       int
);

insert into best_env (flower_species, soil, fertilizer_type, water_type, def_soil, def_fer, def_water)
VALUES ('rose', 'sandy', 'organic', 'bottled', 1, 2, 3),
       ('tulip', 'sandy', 'organic', 'bottled', 10, 20, 30);

CREATE TABLE user_diseases
(
    id             SERIAL PRIMARY KEY,
    flower_id      int references user_flowers (id),
    diseases_state diseases_state,
    diseases_type  diseases_type
);

insert into  user_diseases (flower_id, diseases_state, diseases_type)
VALUES (1, 'started', 'viral'),
       (2, 'continuation', 'fungal');


