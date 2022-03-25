package com.lut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.AdminDao;
import com.lut.pojo.Admin;
import com.lut.pojo.dto.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;


    public ResultData<Admin> Login(String username,String password){
        QueryWrapper<Admin> params = new QueryWrapper<>();
        params.eq("username",username).eq("password",password);
        Admin admin = adminDao.selectOne(params);
        return new ResultData<Admin>().success(admin);
    }

}
