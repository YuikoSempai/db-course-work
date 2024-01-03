package com.yuiko.study.service;

import com.yuiko.study.api.response.Statistic;

public interface FlowerService {

    Statistic getStatistic(Long userId);
}
