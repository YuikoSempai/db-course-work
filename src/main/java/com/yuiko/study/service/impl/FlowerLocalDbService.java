package com.yuiko.study.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yuiko.study.model.CountByType;
import com.yuiko.study.model.Flower;
import com.yuiko.study.service.FlowerDbService;
import org.springframework.stereotype.Service;

@Service
public class FlowerLocalDbService implements FlowerDbService {

    private final List<Flower> flowers = new ArrayList<>();

    @Override
    public List<Flower> getFlowersByUserId(long uid) {
        return flowers.stream().filter(flower -> flower.getUserId() == uid).toList();
    }

    @Override
    public boolean addFlower(long uid, Flower flower) {
        return flowers.add(flower);
    }

    @Override
    public boolean updateFLower(long uid, Flower flower) {
        Integer idx = getFlowerIdxByUid(uid);
        if (idx != null) {
            flowers.set(idx, flower);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFlower(long uid, long flowerId) {
        Integer idx = getFlowerIdxByUid(uid);
        if (idx != null) {
            flowers.remove(idx.intValue());
            return true;
        }
        return false;
    }

    @Override
    public Double getAverageHeight(long uid) {
        double heightSum = 0.0;
        for (Flower flower : flowers) {
            heightSum += flower.getHeight();
        }
        return heightSum / flowers.size();
    }

    @Override
    public Long getCountOfFlowers(long uid) {
        return (long) flowers.size();
    }

    @Override
    public CountByType getCountOfFlowersByType(long uid) {
        // todo implement
        return new CountByType(1);
    }

    @Override
    public String getMostPopularDiseases(long uid) {
        // todo implement
        return "Not implemented :)";
    }

    @Override
    public List<Flower> getAffectedFlowerByLastWeek(long uid) {
        // todo implement
        return List.of();
    }

    @Override
    public Long getFlowerInPerfectPlace(long uid) {
        return 10L;
    }

    private Integer getFlowerIdxByUid(long uid) {
        for (int i = 0; i < flowers.size(); i++) {
            if (flowers.get(i).getUserId() == uid) {
                return i;
            }
        }
        return null;
    }
}
