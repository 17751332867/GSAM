package com.lut;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.DataDao;
import com.lut.dao.FileDataDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    FileDataDao fileDataDao;
    @Test
    void contextLoads() {
        fileDataDao.selectList(new QueryWrapper<>());
    }

}
