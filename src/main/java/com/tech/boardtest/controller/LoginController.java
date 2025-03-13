package com.tech.boardtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String loginHome() {
        System.out.println("loginHomeController");

        return "login/loginHome";
    }
}
