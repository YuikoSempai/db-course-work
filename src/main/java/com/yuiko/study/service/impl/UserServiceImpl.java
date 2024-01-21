package com.yuiko.study.service.impl;

import java.lang.module.InvalidModuleDescriptorException;
import java.util.Optional;

import com.yuiko.study.model.User;
import com.yuiko.study.service.UserDbService;
import com.yuiko.study.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDbService userDbService;

    public UserServiceImpl(UserDbService userDbService) {
        this.userDbService = userDbService;
    }

    @Override
    public Long registerUser(User user) {
        Optional<User> userFromDb = userDbService.findUser(user);
        if (userFromDb.isPresent()) {
            throw new IllegalArgumentException("User already exist");
        }
        return userDbService.addUser(user);
    }

    @Override
    public Long authorizeUser(User user) {
        return userDbService.findUser(user)
                .orElseThrow(
                        InvalidModuleDescriptorException::new
                ).getId();
    }
}
