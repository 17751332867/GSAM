package com.lut.listener;

import com.alibaba.fastjson.JSON;
import com.lut.dao.FileDao;
import com.lut.dao.UserVisualizationDao;
import com.lut.dao.VisualizationDao;
import com.lut.dao.VisualizationImgDao;
import com.lut.pojo.UserVisualization;
import com.lut.pojo.Visualization;
import com.lut.pojo.VisualizationImg;
import com.lut.pojo.vo.IndexingBenchmarkVo;
import com.lut.pojo.vo.VisualizationSaveVo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "visualization_queue")
public class VisualizationListener {
    @Autowired
    private VisualizationDao visualizationDao;
    @Autowired
    private VisualizationImgDao visualizationImgDao;
    @Autowired
    private UserVisualizationDao userVisualizationDao;
    @Autowired
    private FileDao fileDao;

    @RabbitHandler
    public void doExperiment(String msg){
        VisualizationSaveVo visualizationSaveVo = JSON.parseObject(msg, VisualizationSaveVo.class);
        Visualization visualization = new Visualization();
        visualization.setDescription(visualizationSaveVo.getDescription());
        visualization.setName(visualizationSaveVo.getName());
        com.lut.pojo.File file = fileDao.selectById(visualizationSaveVo.getInputId());
        visualization.setInputUrl(file.getUrl());
        VisualizationImg visualizationImg = new VisualizationImg();
        visualizationDao.insert(visualization);
        for (String s : visualizationSaveVo.getOutputList()) {
            visualizationImg.setImgUrl(s);
            visualizationImg.setVisualizationId(visualization.getId());
            visualizationImgDao.insert(visualizationImg);
        }
        UserVisualization userVisualization = new UserVisualization();
        userVisualization.setUserId(visualizationSaveVo.getUserId());
        userVisualization.setVisualizationId(visualization.getId());
        userVisualizationDao.insert(userVisualization);
    }
}
