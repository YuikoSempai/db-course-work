package com.yuiko.study.api.response;

import java.util.List;

import com.yuiko.study.model.CheckedFlower;

public record CheckedFlowerResponse(List<CheckedFlower> isExpected) {
}
