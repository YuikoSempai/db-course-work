package com.yuiko.study.api.response;

import java.util.List;

import com.yuiko.study.model.FlowersBySpeciesCount;

public record Statistic(
        Double height,
        Long flowersCount,
        List<FlowersBySpeciesCount> flowersCountBySpecies,
        String popularDiseases,
        Long countDiseasedFlower,
        Long perfectFlowerCount
) {
}
