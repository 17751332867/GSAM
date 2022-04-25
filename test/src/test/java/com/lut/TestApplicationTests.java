package com.lut;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.*;
import com.lut.pojo.IndexingBenchmark;
import com.lut.pojo.PangenomeFile;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    PangenomeFileDao pangenomeFileDao;

    @SneakyThrows
    @Test
    void contextLoads() {
        List<PangenomeFile> files = pangenomeFileDao.selectByUserId(1);
        System.out.println(files);
    }
}
