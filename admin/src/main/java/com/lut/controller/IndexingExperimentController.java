package com.lut.controller;

import com.lut.pojo.IndexingExperiment;
import com.lut.pojo.vo.ExperimentVo;
import com.lut.pojo.vo.ResultData;
import com.lut.service.IndexingExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("indexingExperiment")
public class IndexingExperimentController {
    @Autowired
    private IndexingExperimentService indexingExperimentService;
    
    @RequestMapping("select")
    public ResultData<List<IndexingExperiment>> select(){
        List<IndexingExperiment> indexingExperiments = indexingExperimentService.selectAll();
        return new ResultData<List<IndexingExperiment>>().success(indexingExperiments);
    }

    @RequestMapping("doExperiment")
    public ResultData<String> doExperiment(@RequestBody ExperimentVo experimentVo){
        indexingExperimentService.doExperiment(experimentVo);
        return new ResultData<String>().success("success");
    }

    @RequestMapping("selectByUserId")
    public ResultData<List<IndexingExperiment>> selectByUserId(Integer id){
        List<IndexingExperiment> list = indexingExperimentService.selectByUserId(id);
        return new ResultData<List<IndexingExperiment>>().success(list);
    }
}
