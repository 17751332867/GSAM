package com.lut.listener;

import com.alibaba.fastjson.JSON;
import com.lut.dao.FileDao;
import com.lut.dao.IndexingBenchmarkResultDao;
import com.lut.dao.IndexingDao;
import com.lut.pojo.File;
import com.lut.pojo.Indexing;
import com.lut.pojo.IndexingBenchmarkResult;
import com.lut.pojo.vo.IndexingBenchmarkVo;
import com.lut.pojo.vo.MemoryInfo;
import com.lut.util.ProcessUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RabbitListener(queues = "indexing_benchmark_queue")
public class IndexingBenchmarkListener {

    @Autowired
    IndexingDao indexingDao;
    @Autowired
    FileDao fileDao;
    @Autowired
    IndexingBenchmarkResultDao indexingBenchmarkResultDao;
    @RabbitHandler
    public void doExperiment(String msg){
        IndexingBenchmarkVo indexingBenchmarkVo = JSON.parseObject(msg, IndexingBenchmarkVo.class);
        File fa = fileDao.selectById(indexingBenchmarkVo.getFaFile());
        File gfa = fileDao.selectById(indexingBenchmarkVo.getGfaFile());
        String benchmarkId = indexingBenchmarkVo.getIndexingBenchmarkId();
        for (Integer algorithm : indexingBenchmarkVo.getAlgorithms()) {
            Indexing indexing = indexingDao.selectById(algorithm);
            experimentAndSave(indexing,gfa,fa,indexingBenchmarkVo.getIndexingBenchmarkId());
        }
        System.out.println(indexingBenchmarkVo);
    }
    public void experimentAndSave(Indexing indexing, File gfa,File fa,String indexingBenchmarkId){
        String cmd = indexing.getCommand();
        File resFile = new File();
        String resName = "res"+ UUID.randomUUID().toString();
        String resPath = "/img/res/"+resName+".gaf";
        String resFileId = UUID.randomUUID().toString();
        resFile.setUrl(resPath);
        resFile.setId(resFileId);
        resFile.setName(resName);
        fileDao.insert(resFile);
        String BASE_DIR = System.getProperty("user.dir");
        cmd = cmd.replace("${gfa}",BASE_DIR+gfa.getUrl());
        cmd = cmd.replace("${fa}",BASE_DIR+fa.getUrl());
        cmd = cmd.replace("${gaf}",BASE_DIR+resFile.getUrl());
        cmd = cmd.replace(indexing.getName(),BASE_DIR+indexing.getFileList().get(0).getUrl());
        cmd = "/usr/bin/time -f '{\"realTime\":\"%e\",\"memory\":%M,\"cpu\":\"%P\"}' "+cmd;
        MemoryInfo memoryInfo = ProcessUtil.doProcess(cmd);
//        System.out.println(cmd);
        IndexingBenchmarkResult indexingBenchmarkResult = new IndexingBenchmarkResult();
        indexingBenchmarkResult.setIndexingBenchmarkId(indexingBenchmarkId);
        indexingBenchmarkResult.setName(indexing.getName());
        indexingBenchmarkResult.setResFileId(resFileId);
        indexingBenchmarkResult.setCpu(memoryInfo.getCpu());
        indexingBenchmarkResult.setMemory(memoryInfo.getMemory());
        indexingBenchmarkResult.setRealTime(memoryInfo.getRealTime());
        indexingBenchmarkResultDao.insert(indexingBenchmarkResult);
    }
}
