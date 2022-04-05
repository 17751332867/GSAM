package com.lut.service;

import com.alibaba.fastjson.JSON;
import com.lut.MQ.SendExperimentMessageMQ;
import com.lut.dao.FileDao;
import com.lut.dao.IndexingBenchmarkDao;
import com.lut.dao.UserIndexingBenchmarkDao;
import com.lut.pojo.IndexingBenchmark;
import com.lut.pojo.UserIndexingBenchmark;
import com.lut.pojo.vo.IndexingBenchmarkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class BenchmarkService {

    @Autowired
    IndexingBenchmarkDao indexingBenchmarkDao;

    @Autowired
    UserIndexingBenchmarkDao userIndexingBenchmarkDao;
    @Autowired
    FileDao fileDao;

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
}
