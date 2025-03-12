package com.tech.boardtest.login.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public String getEncodedPassword(String id);
    public void insertUserInfo(String id, String name, String email, String encodedPwd);
    public String duplicationCheck(String id);
}
