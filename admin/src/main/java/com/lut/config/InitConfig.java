package com.lut.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.ScriptDao;
import com.lut.pojo.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class InitConfig implements ApplicationRunner {
    @Autowired
    ScriptDao scriptDao;
    @Autowired
    private DruidDataSource druidDataSource;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(druidDataSource.getUrl());
        String BASE_DIR = System.getProperty("user.dir");
        List<Script> lists = scriptDao.selectList(new QueryWrapper<>());
        for(Script script:lists){
            String url = script.getUrl();
            File file = new File(BASE_DIR+url);
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
            File parentFile = file.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            if(!file.exists()){
                byte buff[] = script.getFileData();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(buff);
            }
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
        }
    }
}
