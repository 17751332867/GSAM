package com.lut.controller;

import com.lut.pojo.UserInfo;
import com.lut.pojo.vo.ResultData;
import com.lut.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("login")
    public ResultData<UserInfo> login(String username, String password){
        UserInfo userInfo = userInfoService.login(username, password);
        if(userInfo==null){
            return new ResultData<UserInfo>().error(null);
        }
        return new ResultData<UserInfo>().success(userInfo);
    }
}
