package com.lut.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.MQ.SendExperimentMessageMQ;
import com.lut.dao.FileDao;
import com.lut.dao.IndexingBenchmarkDao;
import com.lut.dao.IndexingBenchmarkResultDao;
import com.lut.dao.UserIndexingBenchmarkDao;
import com.lut.pojo.IndexingBenchmark;
import com.lut.pojo.IndexingBenchmarkResult;
import com.lut.pojo.IndexingBenchmarkTable;
import com.lut.pojo.UserIndexingBenchmark;
import com.lut.pojo.vo.CPUInfo;
import com.lut.pojo.vo.IndexingBenchMarkChartData;
import com.lut.pojo.vo.IndexingBenchmarkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IndexBenchmarkService {

    @Autowired
    IndexingBenchmarkDao indexingBenchmarkDao;

    @Autowired
    UserIndexingBenchmarkDao userIndexingBenchmarkDao;
    @Autowired
    FileDao fileDao;

    @Autowired
    IndexingBenchmarkResultDao indexingBenchmarkResultDao;

    @Autowired
    SendExperimentMessageMQ sendExperimentMessageMQ;
    public String doBanchmark(IndexingBenchmarkVo benchmarkVo) {
        IndexingBenchmark indexingBenchmark = new IndexingBenchmark();
        indexingBenchmark.setDescription(benchmarkVo.getDescription());
        indexingBenchmark.setCreateDate(new Date(System.currentTimeMillis()+3600*1000*8));
        indexingBenchmark.setName(benchmarkVo.getName());
        indexingBenchmark.setFaFileId(benchmarkVo.getFaFile());
        indexingBenchmark.setGfaFileId(benchmarkVo.getGfaFile());
        indexingBenchmark.setStatus("等待中");
        String indexingBenchmarkid = UUID.randomUUID().toString();
        indexingBenchmark.setId(indexingBenchmarkid);
        UserIndexingBenchmark userIndexingBenchmark = new UserIndexingBenchmark();
        userIndexingBenchmark.setIndexingBenchmarkId(indexingBenchmarkid);
        userIndexingBenchmark.setUserId(benchmarkVo.getUserId());
        benchmarkVo.setIndexingBenchmarkId(indexingBenchmarkid);
        userIndexingBenchmarkDao.insert(userIndexingBenchmark);
        indexingBenchmarkDao.insert(indexingBenchmark);
        sendExperimentMessageMQ.sendIndexingBenchmarkMessage(JSON.toJSONString(benchmarkVo));
        return "success";
    }

    public List<IndexingBenchmarkTable> selectByUserId(Integer userId) {
        return indexingBenchmarkDao.selectBenchmarkByUserId(userId);
    }

    public IndexingBenchmark selectById(String id) {
        return indexingBenchmarkDao.selectBenchmarkById(id);
    }

    public IndexingBenchMarkChartData getChartInfo(String id) {
        List<IndexingBenchmarkResult> indexingBenchmarkResults = indexingBenchmarkResultDao.selectByIndexingBenchmarkId(id);
        IndexingBenchMarkChartData indexingBenchMarkChartData = new IndexingBenchMarkChartData();
        List<String> names = new ArrayList<>();
        List<Long> memories = new ArrayList<>();
        List<Double> realTimes = new ArrayList<>();
        List<CPUInfo> cpus = new ArrayList<>();
        for (IndexingBenchmarkResult indexingBenchmarkResult : indexingBenchmarkResults) {
            names.add(indexingBenchmarkResult.getName());
            memories.add(indexingBenchmarkResult.getMemory());
            CPUInfo cpuInfo = new CPUInfo();
            cpuInfo.setName(indexingBenchmarkResult.getName());
            cpuInfo.setValue(Integer.parseInt(indexingBenchmarkResult.getCpu().replace("%","")));
            cpus.add(cpuInfo);
            realTimes.add(indexingBenchmarkResult.getRealTime());
        }
        indexingBenchMarkChartData.setCpu(cpus);
        indexingBenchMarkChartData.setMemory(memories);
        indexingBenchMarkChartData.setRealTime(realTimes);
        indexingBenchMarkChartData.setName(names);
        return indexingBenchMarkChartData;
    }

    public List<IndexingBenchmark> selectAll() {
        List<IndexingBenchmark> benchmarkList = indexingBenchmarkDao.selectList(new QueryWrapper<>());
        return benchmarkList;
    }

    public List<IndexingBenchmarkResult> selectBenchmarkResultByBenchmarkId(String id) {
        IndexingBenchmarkResult indexingBenchmarkResult = new IndexingBenchmarkResult();
        indexingBenchmarkResult.setIndexingBenchmarkId(id);
        return indexingBenchmarkResultDao.selectList(new QueryWrapper<>(indexingBenchmarkResult));
    }

    public List<IndexingBenchmark> deleteById(String id) {
        int res = indexingBenchmarkDao.deleteById(id);
        return indexingBenchmarkDao.selectList(new QueryWrapper<>());
    }

    public List<IndexingBenchmark> updateById(IndexingBenchmark indexingBenchmark) {
        int i = indexingBenchmarkDao.updateById(indexingBenchmark);
        return indexingBenchmarkDao.selectList(new QueryWrapper<>());
    }
}
