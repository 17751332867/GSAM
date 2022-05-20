package com.lut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.AdminDao;
import com.lut.pojo.Admin;
import com.lut.pojo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Admin> selectAll() {
        return adminDao.selectList(new QueryWrapper<>());
    }

    public List<Admin> delete(Integer id) {
        int i = adminDao.deleteById(id);
        return adminDao.selectList(new QueryWrapper<>());
    }

    public List<Admin> update(Admin data) {
        int res = adminDao.updateById(data);
        return adminDao.selectList(new QueryWrapper<>());
    }

    public List<Admin> insert(Admin admin){
        int res = adminDao.insert(admin);
        return adminDao.selectList(new QueryWrapper<>());
    }

}
