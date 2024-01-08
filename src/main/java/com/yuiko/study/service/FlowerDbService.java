package com.yuiko.study.service;

import java.util.List;

import com.yuiko.study.api.response.Statistic;
import com.yuiko.study.model.CheckedFlower;
import com.yuiko.study.model.Flower;
import com.yuiko.study.model.Watering;

public interface FlowerDbService {

    List<Flower> getFlowersByUserId(long uid);

    boolean addFlower(long uid, Flower flower);

    boolean updateFLower(long uid, Flower flower);

    boolean deleteFlower(long uid, long flowerId);

    Statistic getStatistic();

    List<CheckedFlower> checkFlowerEnv(long uid);

    boolean waterFlower(long uid, long flowerId);

    List<Watering> getWateringSchedule(long uid);
}
