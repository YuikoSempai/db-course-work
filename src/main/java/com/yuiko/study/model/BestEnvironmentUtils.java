package com.yuiko.study.model;

import com.yuiko.study.model.enums.FertilizerType;
import com.yuiko.study.model.enums.FlowerSpecies;
import com.yuiko.study.model.enums.SoilType;
import com.yuiko.study.model.enums.WaterType;

public class BestEnvironmentUtils {
    public static Environment getBestEnvironmentForFlower(FlowerSpecies flowerSpecies) {
        return switch (flowerSpecies) {
            case tulip -> new Environment(flowerSpecies, SoilType.loam, FertilizerType.organic, WaterType.rainy);
            case sunflower -> new Environment(flowerSpecies, SoilType.clay, FertilizerType.organic, WaterType.rainy);
            case lavender -> new Environment(flowerSpecies, SoilType.loam, FertilizerType.leafy, WaterType.rainy);
            case oak_tree -> new Environment(flowerSpecies, SoilType.loam, FertilizerType.organic, WaterType.borehole);
            case daisy -> new Environment(flowerSpecies, SoilType.sandy, FertilizerType.organic, WaterType.rainy);
            case maple_tree -> new Environment(flowerSpecies, SoilType.peat_bogs, FertilizerType.organic, WaterType.rainy);
            case cactus -> new Environment(flowerSpecies, SoilType.podzolic, FertilizerType.organic, WaterType.rainy);
            case fern -> new Environment(flowerSpecies, SoilType.loam, FertilizerType.mineral, WaterType.rainy);
            case rose -> new Environment(flowerSpecies, SoilType.loam, FertilizerType.biological, WaterType.rainy);
            case daffodil -> new Environment(flowerSpecies, SoilType.loam, FertilizerType.organo_mineral, WaterType.rainy);
        };
    }
}
