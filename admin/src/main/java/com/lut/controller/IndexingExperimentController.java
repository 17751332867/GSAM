package com.lut.controller;

import com.lut.pojo.IndexingExperiment;
import com.lut.pojo.dto.ResultData;
import com.lut.service.IndexingExperimentService;
import com.lut.service.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
