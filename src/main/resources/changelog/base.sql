CREATE TYPE diseases_state AS ENUM ('started', 'continuation', 'advanced');
CREATE TYPE diseases_type AS ENUM ('fungal', 'viral', 'bacterial', 'nematodes', 'physiological');
CREATE TYPE flower_species AS ENUM ('rose', 'tulip', 'sunflower', 'lavender', 'oak_tree', 'daisy', 'maple_tree', 'cactus', 'fern', 'daffodil');
CREATE TYPE resources_type AS ENUM ('sandy', 'loams', 'clayey', 'peat_bogs', 'podzolic', 'crane', 'rainy', 'borehole', 'bottled', 'salty', 'organic', 'mineral', 'organo_mineral', 'biological', 'leafy');
CREATE TYPE object_type AS ENUM ('water', 'fertilizers', 'soil');
CREATE TYPE light_type AS ENUM ('solar', 'phytolamp', 'led');

CREATE TABLE user_flowers
(
    id              SERIAL PRIMARY KEY,
    user_id         bigint,
    flower_species  varchar,
    soil            varchar,
    fertilizer_type varchar,
    water_type      varchar,
    height          REAL
);

create sequence users_seq;
create table users (
                       id int default nextval('users_seq') primary key ,
                       username text,
                       password text,
                       unique (username, password)
);

create table perfect_variant(
    type text primary key,
    soil text not null,
    fertilizer text not null,
    water text not null
);

create table flower_desiases

insert into user_flowers (user_id, flower_species, soil, fertilizer_type, water_type, height)
values (1, 'rose', 'podzolic', 'organic', 'crane', 10),
       (2, 'rose', 'podzolic', 'organic', 'crane', 10);

