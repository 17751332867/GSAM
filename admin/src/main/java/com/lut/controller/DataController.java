package com.lut.controller;

import com.lut.config.ServerConfig;
import com.lut.feign.SendFileMessageController;
import com.lut.pojo.Data;
import com.lut.pojo.dto.FileDto;
import com.lut.pojo.dto.ResultData;
import com.lut.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("data")
public class DataController {

    @Autowired
    private DataService dataService;

    @RequestMapping("upload")
    public ResultData<String> upload(Integer id, MultipartFile file){
        String res = dataService.upload(id, file);
        if(res.equals("success"))
            return new ResultData<String>().success("ok");
        else
            return new ResultData<String>().error("failed");
    }

    @RequestMapping("selectAll")
    public ResultData<List<Data>> selectAll(){
        List<Data> data = dataService.getAllData();
        return new ResultData<List<Data>>().success(data);
    }
}
