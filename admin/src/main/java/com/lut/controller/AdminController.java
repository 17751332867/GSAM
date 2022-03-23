package com.lut.controller;

import com.lut.pojo.Admin;
import com.lut.pojo.dto.ResultData;
import com.lut.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public ResultData<Admin> login(String username,String password){
        return adminService.Login(username,password);
    }
}
