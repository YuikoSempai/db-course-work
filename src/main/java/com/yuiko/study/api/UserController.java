package com.yuiko.study.api;

import com.yuiko.study.api.response.UserResponse;
import com.yuiko.study.model.User;
import com.yuiko.study.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public UserResponse registerUser(
            @RequestBody User user
    ) {
        return new UserResponse(userService.registerUser(user));
    }

    @PostMapping("/login")
    public UserResponse authorizeUser(
            @RequestBody User user
    ) {
        return new UserResponse(userService.authorizeUser(user));
    }
}
