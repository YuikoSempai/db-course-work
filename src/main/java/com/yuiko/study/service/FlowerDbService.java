package com.yuiko.study.service;

import java.util.List;

import com.yuiko.study.model.CountByType;
import com.yuiko.study.model.Flower;

public interface FlowerDbService {

    List<Flower> getFlowersByUserId(long uid);

    boolean addFlower(long uid, Flower flower);

    boolean updateFLower(long uid, Flower flower);

    boolean deleteFlower(long uid, long flowerId);

    Double getAverageHeight(long uid);

    Long getCountOfFlowers(long uid);

    CountByType getCountOfFlowersByType(long uid);

    String getMostPopularDiseases(long uid);

    List<Flower> getAffectedFlowerByLastWeek(long uid);

    Long getFlowerInPerfectPlace(long uid);
}
