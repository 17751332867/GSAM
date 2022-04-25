package com.lut;

import com.alibaba.druid.pool.DruidDataSource;
import com.lut.dao.IndexingDao;
import com.lut.dao.PangenomeFileDao;
import com.lut.pojo.PangenomeFile;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
public class AdminApplicationTests {

    @Autowired
    PangenomeFileDao pangenomeFileDao;

    @SneakyThrows
    @Test
    void contextLoads() {
        List<PangenomeFile> files = pangenomeFileDao.selectByUserId(1);
        System.out.println(files);
    }
}
