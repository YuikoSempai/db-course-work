package com.yuiko.study.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yuiko.study.model.BestEnvironmentUtils;
import com.yuiko.study.model.CheckedFlower;
import com.yuiko.study.model.CountByType;
import com.yuiko.study.model.Environment;
import com.yuiko.study.model.Flower;
import com.yuiko.study.model.enums.FertilizerType;
import com.yuiko.study.model.enums.FlowerSpecies;
import com.yuiko.study.model.enums.SoilType;
import com.yuiko.study.model.enums.WaterType;
import com.yuiko.study.service.FlowerDbService;
import org.springframework.stereotype.Service;

@Service
public class FlowerLocalDbService implements FlowerDbService {

    private final List<Flower> flowers = new ArrayList<>();
    private Long idx = 0L;

    public FlowerLocalDbService() {
        flowers.addAll(List.of(
                new Flower(
                        ++idx,
                        100,
                        FlowerSpecies.fern,
                        SoilType.sandy,
                        FertilizerType.organic,
                        WaterType.rainy,
                        100
                ),
                new Flower(
                        ++idx,
                        100,
                        FlowerSpecies.cactus,
                        SoilType.loam,
                        FertilizerType.organic,
                        WaterType.rainy,
                        200
                )
        ));

    }

    @Override
    public List<Flower> getFlowersByUserId(long uid) {
        return flowers.stream().filter(flower -> flower.getUserId() == uid).toList();
    }

    @Override
    public boolean addFlower(long uid, Flower flower) {
        return flowers.add(flower.setId(++idx));
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

    @Override
    public List<CheckedFlower> checkFlowerEnv(long uid) {
        List<CheckedFlower> checkedFlowers = new ArrayList<>();
        for (Flower flower : flowers) {
            if (flower.getUserId() != uid) {
                continue;
            }
            Environment bestEnv = BestEnvironmentUtils.getBestEnvironmentForFlower(flower.getFlowerSpecies());
            checkedFlowers.add(new CheckedFlower(
                    flower.getId(),
                    flower.getUserId(),
                    flower.getFlowerSpecies(),
                    flower.getSoil() == bestEnv.soilType(),
                    flower.getFertilizerType() == bestEnv.fertilizerType(),
                    flower.getWaterType() == bestEnv.waterType()
            ));
        }
        return checkedFlowers;
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
