package com.yuiko.study.model;

import com.yuiko.study.model.enums.FertilizerType;
import com.yuiko.study.model.enums.FlowerSpecies;
import com.yuiko.study.model.enums.SoilType;
import com.yuiko.study.model.enums.WaterType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class Flower {
    private long id = 0L;
    private long userId;
    private FlowerSpecies flowerSpecies;
    private SoilType soil;
    private FertilizerType fertilizerType;
    private WaterType waterType;
    private double height;

    public Flower(long userId, FlowerSpecies flowerSpecies, SoilType soil, FertilizerType fertilizerType,
                  WaterType waterType, double height) {
        this.userId = userId;
        this.flowerSpecies = flowerSpecies;
        this.soil = soil;
        this.fertilizerType = fertilizerType;
        this.waterType = waterType;
        this.height = height;
    }

    public Flower(long id, long userId, FlowerSpecies flowerSpecies, SoilType soil, FertilizerType fertilizerType,
                  WaterType waterType, double height) {
        this.id = id;
        this.userId = userId;
        this.flowerSpecies = flowerSpecies;
        this.soil = soil;
        this.fertilizerType = fertilizerType;
        this.waterType = waterType;
        this.height = height;
    }

    public Flower setId(long id) {
        this.id = id;
        return this;
    }

    public Flower setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public Flower setFlowerSpecies(FlowerSpecies flowerSpecies) {
        this.flowerSpecies = flowerSpecies;
        return this;
    }

    public Flower setSoil(SoilType soil) {
        this.soil = soil;
        return this;
    }

    public Flower setFertilizerType(FertilizerType fertilizerType) {
        this.fertilizerType = fertilizerType;
        return this;
    }

    public Flower setWaterType(WaterType waterType) {
        this.waterType = waterType;
        return this;
    }

    public Flower setHeight(double height) {
        this.height = height;
        return this;
    }
}
