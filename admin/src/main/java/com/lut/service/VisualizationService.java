package com.lut.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.MQ.SendExperimentMessageMQ;
import com.lut.dao.FileDao;
import com.lut.dao.VisualizationDao;
import com.lut.dao.VisualizationImgDao;
import com.lut.pojo.Visualization;
import com.lut.pojo.VisualizationImg;
import com.lut.pojo.vo.MemoryInfo;
import com.lut.pojo.vo.VisualizationSaveVo;
import com.lut.util.ProcessUtil;
import com.lut.websocket.MessageWebSocket;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.Session;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VisualizationService {
    @Autowired
    private FileDao fileDao;
    @Autowired
    private MessageWebSocket messageWebSocket;
    @Autowired
    private ProcessUtil processUtil;
    @Autowired
    private SendExperimentMessageMQ sendExperimentMessageMQ;
    @Autowired
    private VisualizationDao visualizationDao;
    @Autowired
    private VisualizationImgDao visualizationImgDao;

    public String upload( MultipartFile file) {

        String BASE_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        String path = SEPARATOR+"img"+SEPARATOR+"data"+SEPARATOR+ UUID.randomUUID()+file.getOriginalFilename();
        String destPath = BASE_DIR+path;
        File dest = new File(destPath);
        try {
            File parentFile = dest.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }
        String fileId = UUID.randomUUID().toString();
        com.lut.pojo.File saveFile = new com.lut.pojo.File();

        saveFile.setUrl(path);
        saveFile.setName(file.getOriginalFilename());
        saveFile.setId(fileId);
        fileDao.insert(saveFile);
        return fileId;
    }

    @SneakyThrows
    public List<String> visualize(String id, Integer userId) {
        String BASE_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        String dataPath = BASE_DIR+SEPARATOR+"img"+SEPARATOR+"data";
        com.lut.pojo.File file = fileDao.selectById(id);
        String url = file.getUrl();
        url = url.replace("/img","");
        String randomPath = "/out"+UUID.randomUUID().toString();
        String command = "/usr/bin/time -f '{\"realTime\":\"%e\",\"memory\":%M,\"cpu\":\"%P\"}' "+"docker run -i -v "+dataPath+":/data ghcr.io/pangenome/pggb:202111182259165d2601 \"pggb -i "+url+" -p 70 -s 3000 -G 2000 -n 10 -t 16 -v -V 'gi|568815561:#' -o /data"+randomPath+" -M -m\"";
        MemoryInfo memoryInfo = processUtil.doProcessWithOutputStream(command,userId);
        System.out.println(memoryInfo);
        String outPath  = dataPath+randomPath;
        String dirPath = SEPARATOR+"img"+SEPARATOR+"data"+randomPath+SEPARATOR;
        File dic = new File(outPath);
        File[] files = dic.listFiles();
        List<String> res=new ArrayList<>();
        for(File f:files){
            if(f.getName().endsWith("png")){
                res.add(dirPath+f.getName());
            }
        }
        Session session = messageWebSocket.getSocket(userId);
        session.getBasicRemote().sendText("success:"+ JSON.toJSONString(res));
        return res;
    }

    public void save(VisualizationSaveVo visualizationSaveVo) {
        sendExperimentMessageMQ.sendInsertVisualization(JSON.toJSONString(visualizationSaveVo));
    }

    public List<Visualization> selectByUserId(Integer userId) {
        List<Visualization> list = visualizationDao.selectByUserId(userId);
        return list;
    }

    public List<VisualizationImg> selectVisualizationImgs(Integer id) {
        VisualizationImg visualizationImg = new VisualizationImg();
        visualizationImg.setVisualizationId(id);
        return visualizationImgDao.selectList(new QueryWrapper<>(visualizationImg));
    }

    public List<Visualization> selectAll() {
        List<Visualization> list = visualizationDao.selectList(new QueryWrapper<>());
        return list;
    }

    public List<Visualization> delete(Integer id) {
        int i = visualizationDao.deleteById(id);
        return visualizationDao.selectList(new QueryWrapper<>());
    }

    public List<Visualization> update(Visualization visualization) {
        int i = visualizationDao.updateById(visualization);
        return visualizationDao.selectList(new QueryWrapper<>());
    }
}
