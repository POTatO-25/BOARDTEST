package com.tech.boardtest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardTestController {

    @GetMapping("/")
    public String loginHome() {
        System.out.println("HomeController");

        return "login/loginHome";
    }
}
