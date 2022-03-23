package com.lut;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.AdminDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminApplicationTests {
    @Autowired
    private AdminDao adminDao;

    @Test
    void contextLoads() {
        String BASE_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        String DATA_DIR = BASE_DIR+SEPARATOR+"img"+SEPARATOR+"data";
        System.out.println(DATA_DIR);
        System.out.println(adminDao.selectList(new QueryWrapper<>()));
    }

}
