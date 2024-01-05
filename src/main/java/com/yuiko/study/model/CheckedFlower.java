package com.yuiko.study.model;

import com.yuiko.study.model.enums.FlowerSpecies;

public record CheckedFlower(
        long id,
        long userId,
        FlowerSpecies flowerSpecies,
        boolean soil,
        boolean fertilizerType,
        boolean waterType
) {
}
