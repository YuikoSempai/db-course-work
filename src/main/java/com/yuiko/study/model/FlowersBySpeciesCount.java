package com.yuiko.study.model;

import com.yuiko.study.model.enums.FlowerSpecies;

public record FlowersBySpeciesCount(
        FlowerSpecies flowerSpices,
        Long count
) {
}
