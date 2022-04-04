package com.lut.service;

import com.lut.config.ServerConfig;
import com.lut.dao.FileDao;
import com.lut.dao.IndexingDao;
import com.lut.pojo.Indexing;
import com.lut.pojo.vo.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class IndexingService {
    @Autowired
    private IndexingDao indexingDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private ServerConfig serverConfig;


    public List<Indexing> selectAll(){
        return indexingDao.selectAll();
    }

    public String upload(Integer id, MultipartFile file) {
        String BASE_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        String path = SEPARATOR+"img"+SEPARATOR+"indexing"+SEPARATOR+ UUID.randomUUID()+file.getOriginalFilename();
        String destPath = BASE_DIR+path;
        File dest = new File(destPath);
        try {
            File parentFile = dest.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            file.transferTo(dest);
            dest.setExecutable(true);
            dest.setReadable(true);
            dest.setWritable(true);
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }
        String fileId = UUID.randomUUID().toString();
        com.lut.pojo.File saveFile = new com.lut.pojo.File();
        saveFile.setId(fileId);
        saveFile.setName(file.getOriginalFilename());
        saveFile.setUrl(path);
        fileDao.insert(saveFile);

        Indexing indexing = indexingDao.selectById(id);
        indexing.setFileId(fileId);
        indexingDao.updateById(indexing);
        FileDto fileDto = new FileDto();
        fileDto.setUrl(serverConfig.getUrl());
        fileDto.setPath(path);
        fileDto.setFileId(fileId);

        return "success";
    }

    public List<Indexing> insert(Indexing indexing) {
        indexingDao.insert(indexing);
        List<Indexing> indexings = indexingDao.selectAll();
        return indexings;
    }

    public List<Indexing> upload(Indexing indexing) {
        int res = indexingDao.updateById(indexing);
        return indexingDao.selectAll();
    }

    public List<Indexing> delete(Integer id) {
        int res = indexingDao.deleteById(id);
        List<Indexing> indexings = indexingDao.selectAll();
        return indexings;
    }
}
