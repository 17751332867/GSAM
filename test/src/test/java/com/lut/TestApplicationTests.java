package com.lut;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.AlgorithmDao;
import com.lut.dao.DataDao;
import com.lut.dao.FileDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    DataDao dataDao;
    @Test
    void contextLoads() {
        System.out.println(dataDao.selectAll());
    }

}
