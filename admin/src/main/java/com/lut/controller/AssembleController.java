package com.lut.controller;

import com.lut.pojo.Assemble;
import com.lut.pojo.vo.ResultData;
import com.lut.service.AssembleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("assemble")
public class AssembleController {

    @Autowired
    private AssembleService assembleService;

    @RequestMapping("selectAll")
    public ResultData<List<Assemble>> selectAll(){
        List<Assemble> assembleList = assembleService.selectAll();
        return new ResultData<List<Assemble>>().success(assembleList);
    }

    @RequestMapping("addAssemble")
    public ResultData<String> resultData(Assemble assemble){
        String res = assembleService.addAssemble(assemble);
        if(res.equals("success")){
            return new ResultData<String>().success("success");
        }else{
            return new ResultData<String>().error("error");
        }
    }
}
