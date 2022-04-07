package com.lut.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lut.pojo.IndexingBenchmark;
import com.lut.pojo.IndexingBenchmarkTable;
import com.lut.pojo.vo.IndexingBenchMarkChartData;
import com.lut.pojo.vo.IndexingBenchmarkVo;
import com.lut.pojo.vo.ResultData;
import com.lut.service.IndexBenchmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("benchmark")
public class BenchmarkController {

    @Autowired
    IndexBenchmarkService benchmarkService;

    @RequestMapping("doBanchmark")
    public ResultData<String> doBanchmark(@RequestBody IndexingBenchmarkVo benchmarkVo){
        String s = benchmarkService.doBanchmark(benchmarkVo);
        if(s.equals("success"))
            return new ResultData<String>().success("success");
        return new ResultData<String>().error("error");
    }

    @RequestMapping("selectByUserId")
    public ResultData<List<IndexingBenchmarkTable>> selectByUserId(Integer userId){
        List<IndexingBenchmarkTable> list = benchmarkService.selectByUserId(userId);
        return new ResultData<List<IndexingBenchmarkTable>>().success(list);
    }
    @RequestMapping("selectById")
    public ResultData<IndexingBenchmark> selectById(String id){
        IndexingBenchmark benchmark = benchmarkService.selectById(id);
        return new ResultData<IndexingBenchmark>().success(benchmark);
    }
    @RequestMapping("getChartInfo")
    public ResultData<IndexingBenchMarkChartData> getChartInfo(String id){
        IndexingBenchMarkChartData indexingBenchMarkChartData = benchmarkService.getChartInfo(id);
        return new ResultData<IndexingBenchMarkChartData>().success(indexingBenchMarkChartData);
    }
}
