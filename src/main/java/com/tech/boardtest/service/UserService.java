package com.tech.boardtest.service;

import com.tech.boardtest.dto.LoginRequest;
import com.tech.boardtest.entity.User;
import com.tech.boardtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean validateUser(LoginRequest loginRequest) {
        User user = userRepository.findByUserId(loginRequest.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + loginRequest.getId()));

        return user.getUserPwd().equals(loginRequest.getPwd());
    }
}
