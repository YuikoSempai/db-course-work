package com.yuiko.study.api.response;

import java.util.List;

import com.yuiko.study.model.Environment;

public record EnvironmentPage(List<Environment> bestEnvironments) {
}
