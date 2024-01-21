package com.yuiko.study.model;

import com.yuiko.study.model.enums.FertilizerType;
import com.yuiko.study.model.enums.FlowerSpecies;
import com.yuiko.study.model.enums.SoilType;
import com.yuiko.study.model.enums.WaterType;

public record Environment(
        FlowerSpecies flowerSpecies,
        SoilType soil,
        FertilizerType fertilizerType,
        WaterType waterType
) {
}
