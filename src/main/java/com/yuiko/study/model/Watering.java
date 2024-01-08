package com.yuiko.study.model;

public record Watering(
        long id,
        long user_id,
        boolean needWater,
        String nextWatering
) {
}
