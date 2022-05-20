package com.lut.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lut.pojo.Admin;
import com.lut.pojo.vo.ResultData;
import com.lut.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public ResultData<Admin> login(String username,String password){
        return adminService.Login(username,password);
    }
    @RequestMapping("selectAll")
    public ResultData<List<Admin>> selectAll(){
        List<Admin> list = adminService.selectAll();
        return new ResultData<List<Admin>>().success(list);
    }
    @RequestMapping("delete")
    public ResultData<List<Admin>> delete(Integer id){
        List<Admin> list = adminService.delete(id);
        return new ResultData<List<Admin>>().success(list);
    }
    @RequestMapping("update")
    public ResultData<List<Admin>> update(@RequestBody Admin data){
        System.out.println(data);
        List<Admin> list = adminService.update(data);
        return new ResultData<List<Admin>>().success(list);
    }
    @RequestMapping("insert")
    public ResultData<List<Admin>> insert(@RequestBody Admin admin){
        System.out.println(admin);
        List<Admin> list = adminService.insert(admin);
        return new ResultData<List<Admin>>().success(list);
    }
}
