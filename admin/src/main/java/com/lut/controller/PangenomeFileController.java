package com.lut.controller;

import com.lut.pojo.PangenomeFile;
import com.lut.pojo.vo.PangenomeVo;
import com.lut.pojo.vo.ResultData;
import com.lut.service.PangenomeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("PangenomeFile")
public class PangenomeFileController {
    @Autowired
    private PangenomeFileService pangenomeFileService;

    @RequestMapping("insert")
    public ResultData<PangenomeFile> insertPangenomeFile(@RequestBody PangenomeVo pangenomeVo){

        PangenomeFile pangenomeFile = pangenomeFileService.insertPangenomeFile(pangenomeVo);
        return new ResultData<PangenomeFile>().success(pangenomeFile);
    }
    @RequestMapping("selectByUserId")
    public ResultData<List<PangenomeFile>> selectByUserId(Integer userId){
        List<PangenomeFile> list = pangenomeFileService.selectByUserId(userId);
        return new ResultData<List<PangenomeFile>>().success(list);
    }

    @RequestMapping("delete")
    public ResultData<List<PangenomeFile>> delete(String id){
        System.out.println(id);
        List<PangenomeFile> list = pangenomeFileService.delete(id);
        return new ResultData<List<PangenomeFile>>().success(list);
    }

    @RequestMapping("selectAll")
    public ResultData<List<PangenomeFile>> selectAll(){
        List<PangenomeFile> list = pangenomeFileService.selectAll();
        return new ResultData<List<PangenomeFile>>().success(list);
    }

    @RequestMapping("redo")
    public ResultData<List<PangenomeFile>> redo(@RequestBody PangenomeFile pangenomeFile){
        List<PangenomeFile> list = pangenomeFileService.redo(pangenomeFile);
        return new ResultData<List<PangenomeFile>>().success(list);
    }
}
