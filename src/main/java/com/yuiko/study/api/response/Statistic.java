package com.yuiko.study.api.response;

import java.util.Map;

public record Statistic(
        Double height,
        Long flowersCount,
        Map<String, Long> flowersCountBySpecies,
        String popularDiseases,
        Long countDiseasedFlower,
        Long perfectFlowerCount
) {
}
