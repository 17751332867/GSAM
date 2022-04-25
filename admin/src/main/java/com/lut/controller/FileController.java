package com.lut.controller;

import com.lut.pojo.File;
import com.lut.pojo.vo.ResultData;
import com.lut.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("upload")
    public ResultData<File> upload(MultipartFile file){
        File res = fileService.upload(file);
        if(res==null){
            return new ResultData<File>().error(res);
        }
        return new ResultData<File>().success(res);
    }

    @RequestMapping("doExperiment")
    public ResultData<String> doExperiment(){
        return new ResultData<String>().success("success");
    }

    @RequestMapping("selectById")
    public ResultData<File> selectById(String id){
        System.out.println(id);
        File file = fileService.selectById(id);
        System.out.println(file);
        return new ResultData<File>().success(file);
    }
}
