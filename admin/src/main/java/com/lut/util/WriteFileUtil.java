package com.lut.util;

import com.lut.dao.FileDataDao;
import com.lut.pojo.FileData;
import com.lut.pojo.vo.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WriteFileUtil {
    @Autowired
    FileDataDao fileDataDao;
    @Autowired
    RestTemplate restTemplate;

    public void ReadAndWriteTodb(FileDto fileDto){
        String url = fileDto.getUrl()+fileDto.getPath();
        url = url.replace("\\","/");
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
        byte[] bytes = responseEntity.getBody();
        FileData fileData = new FileData();
        fileData.setFileData(bytes);
        fileData.setFileId(fileDto.getFileId());
        fileDataDao.insert(fileData);
    }
}
