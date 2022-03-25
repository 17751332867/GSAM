package com.lut.controller;

import com.lut.pojo.Indexing;
import com.lut.pojo.dto.ResultData;
import com.lut.service.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("indexing")
public class IndexingController {

    @Autowired
    private IndexingService indexingService;

    @RequestMapping("selectAll")
    public ResultData<List<Indexing>> selectAll(){
        List<Indexing> indexings = indexingService.selectAll();
        return new ResultData<List<Indexing>>().success(indexings);
    }

}
