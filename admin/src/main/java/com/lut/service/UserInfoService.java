package com.lut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.UserInfoDao;
import com.lut.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo login(String username,String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>(userInfo);
        UserInfo info = userInfoDao.selectOne(wrapper);
        return info;
    }

    public List<UserInfo> selectList() {
        return userInfoDao.selectList(new QueryWrapper<>());
    }

    public List<UserInfo> insert(UserInfo userInfo) {
        int res = userInfoDao.insert(userInfo);
        return userInfoDao.selectList(new QueryWrapper<>());
    }

    public List<UserInfo> update(UserInfo userInfo) {
        int res = userInfoDao.updateById(userInfo);
        return userInfoDao.selectList(new QueryWrapper<>());
    }

    public List<UserInfo> delete(Integer id) {
        int res = userInfoDao.deleteById(id);
        return userInfoDao.selectList(new QueryWrapper<>());
    }
}
