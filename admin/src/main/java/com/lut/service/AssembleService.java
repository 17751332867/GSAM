package com.lut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.AssembleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssembleService {
    @Autowired
    private AssembleDao assembleDao;

    public List<Assemble> selectAll(){
        List<Assemble> list = assembleDao.selectList(new QueryWrapper<>());
        return list;
    }

    public String addAssemble(Assemble assemble){
        int res = assembleDao.insert(assemble);
        if(res!=0){
            return "success";
        }else{
            return "error";
        }
    }
}
