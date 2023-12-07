package com.yuiko.study.service;

import java.util.List;

import com.yuiko.study.model.Flower;

public interface FlowerService {

    List<Flower> getFlowersByUserId(Long uid);
}
