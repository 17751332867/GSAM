package com.lut.service;

import com.lut.dao.FileDao;
import com.lut.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileDao fileDao;

    @RequestMapping("upload")
    public File upload(MultipartFile file){
        String BASE_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        String path = SEPARATOR+"img"+SEPARATOR+"file"+SEPARATOR+ UUID.randomUUID()+file.getOriginalFilename();
        String destPath = BASE_DIR+path;
        java.io.File dest = new java.io.File(destPath);
        try {
            java.io.File parentFile = dest.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String fileId = UUID.randomUUID().toString();

        File saveFile = new File();
        saveFile.setUrl(path);
        saveFile.setName(file.getOriginalFilename());
        saveFile.setId(fileId);
        fileDao.insert(saveFile);
        return saveFile;
    }
}
