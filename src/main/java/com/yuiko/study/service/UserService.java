package com.yuiko.study.service;

import com.yuiko.study.model.User;

public interface UserService {
    Long registerUser(User user);
    Long authorizeUser(User user);
}
