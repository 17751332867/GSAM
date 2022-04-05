package com.lut.controller;

import com.lut.pojo.vo.IndexingBenchmarkVo;
import com.lut.pojo.vo.ResultData;
import com.lut.service.BenchmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("benchmark")
public class BenchmarkController {

    @Autowired
    BenchmarkService benchmarkService;

    @RequestMapping("doBanchmark")
    public ResultData<String> doBanchmark(@RequestBody IndexingBenchmarkVo benchmarkVo){
        String s = benchmarkService.doBanchmark(benchmarkVo);
        if(s.equals("success"))
            return new ResultData<String>().success("success");
        return new ResultData<String>().error("error");
    }

}
