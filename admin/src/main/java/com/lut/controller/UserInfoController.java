package com.lut.controller;

import com.lut.pojo.UserInfo;
import com.lut.pojo.vo.ResultData;
import com.lut.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("selectAll")
    public ResultData<List<UserInfo>> selectAll(){
        List<UserInfo> list = userInfoService.selectList();
        return new ResultData<List<UserInfo>>().success(list);
    }

    @RequestMapping("insert")
    public ResultData<List<UserInfo>> insert(@RequestBody UserInfo userInfo){
        List<UserInfo> list = userInfoService.insert(userInfo);
        return new ResultData<List<UserInfo>>().success(list);
    }
    @RequestMapping("update")
    public ResultData<List<UserInfo>> update(@RequestBody UserInfo userInfo){
        List<UserInfo> list = userInfoService.update(userInfo);
        return new ResultData<List<UserInfo>>().success(list);
    }
    @RequestMapping("delete")
    public ResultData<List<UserInfo>> delete(Integer id){
        List<UserInfo> list = userInfoService.delete(id);
        return new ResultData<List<UserInfo>>().success(list);
    }
}
