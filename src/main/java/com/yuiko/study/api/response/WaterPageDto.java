package com.yuiko.study.api.response;

import java.util.List;

import com.yuiko.study.model.Watering;

public record WaterPageDto(List<Watering> waterSchedule) {
}
