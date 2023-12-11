package com.yuiko.study.service;

import java.util.List;

import com.yuiko.study.api.response.ResponseWithMessage;
import com.yuiko.study.model.Flower;

public interface FlowerDbService {

    List<Flower> getFlowersByUserId(long uid);

    ResponseWithMessage addFlower(long uid, Flower flower);

    ResponseWithMessage updateFLower(long uid, Flower flower);

    ResponseWithMessage deleteFlower(long uid, long flowerId);
}
