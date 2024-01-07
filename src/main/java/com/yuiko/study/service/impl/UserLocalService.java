package com.yuiko.study.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.yuiko.study.model.User;
import com.yuiko.study.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserLocalService implements UserService {

    private final List<User> users = new ArrayList<>();
    private long idx = 0L;

    public UserLocalService() {
        users.addAll(List.of(
                new User(
                        ++idx,
                        "Vlad",
                        "123"
                ),
                new User(
                        ++idx,
                        "Ira",
                        "123"
                )
        ));
    }

    @Override
    public Long registerUser(User user) {
        users.add(user);
        user.setId(++idx);
        return (long) users.size();
    }

    @Override
    public Long authorizeUser(User user) {
        return users.stream().filter(u ->
                        Objects.equals(u.getUsername(), user.getUsername())
                                && Objects.equals(u.getPassword(), user.getPassword())
                ).map(User::getId)
                .findFirst().orElse(null);
    }
}
