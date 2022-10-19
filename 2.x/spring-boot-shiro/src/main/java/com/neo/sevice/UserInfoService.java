package com.neo.sevice;

import com.neo.model.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}