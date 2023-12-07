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
    flower_species  flower_species,
    soil            resources_type,
    fertilizer_type resources_type,
    water_type      resources_type,
    height          REAL
);

insert into user_flowers (user_id, flower_species, soil, fertilizer_type, water_type, height)
values (1, 'rose', 'podzolic', 'organic', 'crane', 10),
       (2, 'rose', 'podzolic', 'organic', 'crane', 10);