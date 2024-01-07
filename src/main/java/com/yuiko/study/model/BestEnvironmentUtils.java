package com.yuiko.study.model;

import com.yuiko.study.model.enums.FertilizerType;
import com.yuiko.study.model.enums.FlowerSpecies;
import com.yuiko.study.model.enums.SoilType;
import com.yuiko.study.model.enums.WaterType;

public class BestEnvironmentUtils {
    public static Environment getBestEnvironmentForFlower(FlowerSpecies flowerSpecies) {
        return switch (flowerSpecies) {
            case tulip -> new Environment(flowerSpecies, SoilType.podzolic, FertilizerType.organic, WaterType.rainy);
            case sunflower -> new Environment(flowerSpecies, SoilType.clayey, FertilizerType.organic, WaterType.rainy);
            case lavender -> new Environment(flowerSpecies, SoilType.podzolic, FertilizerType.potassium_nitrate, WaterType.rainy);
            case oak_tree -> new Environment(flowerSpecies, SoilType.podzolic, FertilizerType.organic, WaterType.salty);
            case daisy -> new Environment(flowerSpecies, SoilType.sandy, FertilizerType.organic, WaterType.rainy);
            case maple_tree -> new Environment(flowerSpecies, SoilType.peat, FertilizerType.organic, WaterType.rainy);
            case cactus -> new Environment(flowerSpecies, SoilType.podzolic, FertilizerType.organic, WaterType.bottled);
            case fern -> new Environment(flowerSpecies, SoilType.podzolic, FertilizerType.ammophos, WaterType.rainy);
            case rose -> new Environment(flowerSpecies, SoilType.podzolic, FertilizerType.nitroammophoska, WaterType.rainy);
            case daffodil -> new Environment(flowerSpecies, SoilType.podzolic, FertilizerType.ammophos, WaterType.bottled);
        };
    }
}
