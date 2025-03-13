package com.tech.boardtest.controller;

import com.tech.boardtest.dto.LoginRequest;
import com.tech.boardtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean validated = userService.validateUser(loginRequest);

        return validated ? "success" : "fail";
    }
}
