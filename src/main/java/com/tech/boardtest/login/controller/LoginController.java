package com.tech.boardtest.login.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginHome() {
        System.out.println("LoginHomeController");

        return "login/loginHome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("logoutController");
        session.removeAttribute("session_user_id");

        return "login/loginHome";
    }
}
