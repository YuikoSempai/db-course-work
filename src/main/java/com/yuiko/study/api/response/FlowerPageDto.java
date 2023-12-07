package com.yuiko.study.api.response;

import java.util.List;

import com.yuiko.study.model.Flower;

public class FlowerPageDto {
    private List<Flower> flowerList;

    public FlowerPageDto(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    public List<Flower> getFlowerList() {
        return flowerList;
    }

    public void setFlowerList(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }
}
