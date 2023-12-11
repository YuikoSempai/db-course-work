package com.yuiko.study.service;

import java.util.Optional;

import com.yuiko.study.model.User;

public interface UserDbService {

    Long addUser(User user);
    Optional<User> findUser(User user);
}
