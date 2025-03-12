package com.tech.boardtest.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String user_id;
    private int user_number;
    private String user_name;
    private String user_email;
    private String user_pwd;
}
