package com.lut.listener;

import com.alibaba.fastjson.JSON;
import com.lut.dao.FileDao;
import com.lut.dao.IndexingDao;
import com.lut.dao.IndexingExperimentDao;
import com.lut.pojo.File;
import com.lut.pojo.Indexing;
import com.lut.pojo.IndexingExperiment;
import com.lut.pojo.vo.MemoryInfo;
import com.lut.util.ProcessUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RabbitListener(queues = "experiment_queue")
public class ExperimentListener {

    @Autowired
    private IndexingExperimentDao indexingExperimentDao;

    @Autowired
    private IndexingDao indexingDao;

    @Autowired
    private FileDao fileDao;

    @RabbitHandler
    public void doExperiment(String msg){
        IndexingExperiment indexingExperiment = JSON.parseObject(msg, IndexingExperiment.class);
        indexingExperiment.setStatus("运行中");
        indexingExperimentDao.updateById(indexingExperiment);
        String BASE_DIR = System.getProperty("user.dir");
        File faFile = fileDao.selectById(indexingExperiment.getFaFileId());
        File gfaFile = fileDao.selectById(indexingExperiment.getGfaFileId());
        Indexing indexing = indexingDao.selectById(indexingExperiment.getIndexingId());
        String cmd = indexing.getCommand();
        File resFile = new File();
        String resName = "res"+ UUID.randomUUID().toString();
        String resPath = "/img/res/"+resName+".gaf";
        resFile.setUrl(resPath);
        resFile.setId(UUID.randomUUID().toString());
        resFile.setName(resName);
        fileDao.insert(resFile);
        cmd = cmd.replace("${gfa}",BASE_DIR+gfaFile.getUrl());
        cmd = cmd.replace("${fa}",BASE_DIR+faFile.getUrl());
        cmd = cmd.replace("${gaf}",BASE_DIR+resFile.getUrl());
        cmd = cmd.replace(indexing.getName(),BASE_DIR+indexing.getFileList().get(0).getUrl());
        cmd = "/usr/bin/time -f '{\"realTime\":\"%e\",\"memory\":%M,\"cpu\":\"%P\"}' "+cmd;
        MemoryInfo memoryInfo = ProcessUtil.doProcess(cmd);
        indexingExperiment.setRunningTime(memoryInfo.getRealTime());
        indexingExperiment.setMemory(memoryInfo.getMemory());
        indexingExperiment.setStatus("完成");
        indexingExperiment.setResFileId(resFile.getId());
        indexingExperimentDao.updateById(indexingExperiment);
    }
}
