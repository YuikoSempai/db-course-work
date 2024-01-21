package com.yuiko.study.service;

import java.util.List;

import com.yuiko.study.model.Environment;

public interface AdminDbService {
    boolean addNewEnvironment(Environment environment);

    List<Environment> getAllEnvironments();
}
