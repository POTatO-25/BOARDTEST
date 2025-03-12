package com.tech.boardtest.login.controller;

import com.tech.boardtest.login.dao.UserDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class JoinController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserDao userDao;

    @Autowired
    public JoinController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/joinHome")
    public String joinHome() {
        System.out.println("joinHomeController");

        return "login/joinHome";
    }

    @PostMapping("/join")
    public String join(@RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("pwd") String pwd, @RequestParam("email") String email) {
        System.out.println("joinController");

        if(name.trim().isEmpty() || id.trim().isEmpty() || pwd.trim().isEmpty() || email.trim().isEmpty()) {
            System.out.println("join error");

            return "redirect:/joinHome";
        }

        // id 중복확인
        // 사용자가 입력한 아이디를 DB에서 조회(user 테이블에서 user_id가 사용자가 입력한 것인 것)한 결과를 String으로 받음
        // 이 결과가 null이면 아이디 사용 가능, null이 아니면 아이디 중복이기 때문에 리턴으로 response Map 형태로 status, 아이디 중복 이렇게 주기
        // 그럼 프론트에서 받아서 script로 처리

        // 비밀번호

        // 회원가입 처리
        String encodedPwd = bCryptPasswordEncoder.encode(pwd);
        userDao.insertUserInfo(id, name, email, encodedPwd);

        return "login/loginHome";
    }

    @PostMapping("/duplicationCheck")
    @ResponseBody
    public Map<String, String> duplicationCheck(@RequestParam("id") String id) {
        System.out.println("duplicationCheckController");

        Map<String, String> response = new HashMap<>();

        String userId = userDao.duplicationCheck(id);

        if(userId == null) {
            response.put("status", "사용 가능");
        } else {
            response.put("status", "사용 불가능");
        }

        return response;
    }
}
