package com.lut.service;

import com.alibaba.fastjson.JSON;
import com.lut.MQ.SendExperimentMessageMQ;
import com.lut.dao.*;
import com.lut.pojo.IndexingExperiment;
import com.lut.pojo.UserIndexingExperiment;
import com.lut.pojo.vo.ExperimentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IndexingExperimentService {
    @Autowired
    private IndexingExperimentDao indexingExperimentDao;

    @Autowired
    private UserIndexingExperimentDao userIndexingExperimentDao;

    @Autowired
    private SendExperimentMessageMQ sendExperimentMessageMQ;

    public List<IndexingExperiment> selectAll(){
        return indexingExperimentDao.selectAll();
    }

    public String doExperiment(ExperimentVo experimentVo) {
        IndexingExperiment indexingExperiment = new IndexingExperiment();
        String indexingExperimentId = UUID.randomUUID().toString();
        indexingExperiment.setId(indexingExperimentId);
        indexingExperiment.setCreateDate(new Date(System.currentTimeMillis()+1000*60*60*8));
        indexingExperiment.setIndexingId(experimentVo.getAlgorithmId());
        indexingExperiment.setName(experimentVo.getName());
        indexingExperiment.setFaFileId(experimentVo.getFaFile());
        indexingExperiment.setGfaFileId(experimentVo.getGfaFile());
        indexingExperiment.setStatus("等待中");
        indexingExperiment.setDescription(experimentVo.getDescription());
        indexingExperimentDao.insert(indexingExperiment);
        UserIndexingExperiment userIndexingExperiment = new UserIndexingExperiment();
        userIndexingExperiment.setUserInfoId(experimentVo.getUserId());
        userIndexingExperiment.setIndexingExperimentId(indexingExperimentId);
        userIndexingExperimentDao.insert(userIndexingExperiment);
        sendExperimentMessageMQ.sendExperimentMessage(JSON.toJSONString(indexingExperiment));
        return "success";
    }

    public List<IndexingExperiment> selectByUserId(Integer id) {
        return indexingExperimentDao.selectIndexingExperimentByUserId(id);
    }
}
