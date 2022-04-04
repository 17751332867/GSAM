package com.lut.listener;

import com.alibaba.fastjson.JSON;
import com.lut.dao.FileDao;
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
    @RabbitHandler
    public void doExperiment(String msg){
        IndexingBenchmarkVo indexingBenchmarkVo = JSON.parseObject(msg, IndexingBenchmarkVo.class);
        for (Integer algorithm : indexingBenchmarkVo.getAlgorithms()) {
            Indexing indexing = indexingDao.selectById(algorithm);

        }
    }
    public void Experiment(Indexing indexing, File gfa,File fa){
        String cmd = indexing.getCommand();
        File resFile = new File();
        String resName = "res"+ UUID.randomUUID().toString();
        String resPath = "/img/res/"+resName+".gaf";
        resFile.setUrl(resPath);
        resFile.setId(UUID.randomUUID().toString());
        resFile.setName(resName);
        fileDao.insert(resFile);
        String BASE_DIR = System.getProperty("user.dir");
        cmd = cmd.replace("${gfa}",BASE_DIR+gfa.getUrl());
        cmd = cmd.replace("${fa}",BASE_DIR+fa.getUrl());
        cmd = cmd.replace("${gaf}",BASE_DIR+resFile.getUrl());
        cmd = cmd.replace(indexing.getName(),BASE_DIR+indexing.getFileList().get(0).getUrl());
        cmd = "/usr/bin/time -f '{\"realTime\":\"%e\",\"memory\":%M,\"cpu\":\"%P\"}' "+cmd;
        MemoryInfo memoryInfo = ProcessUtil.doProcess(cmd);
        IndexingBenchmarkResult indexingBenchmarkResult = new IndexingBenchmarkResult();
    }
}
