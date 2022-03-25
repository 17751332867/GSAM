package com.lut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.config.ServerConfig;
import com.lut.dao.DataDao;
import com.lut.dao.FileDao;
import com.lut.feign.SendFileMessageController;
import com.lut.pojo.Data;
import com.lut.pojo.dto.FileDto;
import com.lut.pojo.dto.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class DataService {
    @Autowired
    private DataDao dataDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    SendFileMessageController sendFileMessageController;

    public List<Data> getAllData(){
        return dataDao.selectAll();
    }

    public String upload(Integer id, MultipartFile file) {
        String BASE_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        String DATA_DIR = BASE_DIR+SEPARATOR+"img"+SEPARATOR+"data";
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


        Data data = dataDao.selectById(id);
        data.setSize(file.getSize());
        dataDao.updateById(data);

        com.lut.pojo.File saveFile = new com.lut.pojo.File();
        String fileId = UUID.randomUUID().toString();
        saveFile.setDataId(id);
        saveFile.setUrl(path);
        saveFile.setName(file.getOriginalFilename());
        saveFile.setId(fileId);
        fileDao.insert(saveFile);


        FileDto fileDto = new FileDto();
        fileDto.setUrl(serverConfig.getUrl());
        fileDto.setPath(path);
        fileDto.setFileId(fileId);
        sendFileMessageController.sendFileMessage(fileDto);
        return "success";
    }
}
