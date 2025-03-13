package com.tech.boardtest.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_number")
    private Integer userNumber;

    @Column(name = "user_id", length = 10, nullable = false, unique = true)
    private String userId;

    @Column(name = "user_name", length = 15, nullable = false)
    private String userName;

    @Column(name = "user_pwd", length = 500)
    private String userPwd;

    @Column(name = "user_email", length = 30)
    private String userEmail;
}
