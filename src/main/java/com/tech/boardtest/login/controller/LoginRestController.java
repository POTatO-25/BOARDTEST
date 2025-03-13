package com.tech.boardtest.login.controller;

import com.tech.boardtest.login.dao.UserDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginRestController {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserDao userDao;

    public LoginRestController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping(("/login"))
    @ResponseBody
    public Map<String, String> login(@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session) {
        System.out.println("loginController");

        // 아이디 또는 비번을 입력하지 앉았을 경우
        if((id.trim().isEmpty() || pwd.trim().isEmpty())) {
            System.out.println("아이디 또는 비번 입력 안함");

            Map<String, String> response = new HashMap();
            response.put("status", "noIdOrPwd");
            return response;
        }

        String encodedPwd = userDao.getEncodedPassword(id);
        boolean isAuthentication = passwordEncoder.matches(pwd, encodedPwd); // 일치하면 true 반환

        Map<String, String> response = new HashMap();

        if(isAuthentication) {
            session.setAttribute("session_user_id", id);
            response.put("status", "success");
            return response;
        } else if (encodedPwd != null && isAuthentication == false) {
            response.put("status", "pwdError");
            return response;
        } else if (encodedPwd == null) {
            response.put("status", "userError");
            return response;
        }

        return response;
    }
}
