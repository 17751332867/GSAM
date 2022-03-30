package com.lut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.UserInfoDao;
import com.lut.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo login(String username,String password){
        UserInfo userInfo = new UserInfo();
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>(userInfo);
        UserInfo info = userInfoDao.selectOne(wrapper);
        return info;
    }
}
