package com.tech.boardtest.login.controller;

import com.tech.boardtest.login.dao.UserDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserDao userDao;

    @Autowired
    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String loginHome() {
        System.out.println("LoginHomeController");

        return "login/loginHome";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session) {
        System.out.println("loginController");

        String encodedPwd = userDao.getEncodedPassword(id);

        if((id.trim().isEmpty() || pwd.trim().isEmpty()) || encodedPwd == null) {
            System.out.println("login error");
            return "redirect:/";
        }

        boolean isAuthentication = passwordEncoder.matches(pwd, encodedPwd); // 일치하면 true 반환

        if(isAuthentication) {
            session.setAttribute("session_user_id", id);
            return "board/boardHome";
        }

        return "redirect:/login/loginHome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("logoutController");

        session.removeAttribute("session_user_id");

        return "login/loginHome";
    }
}
