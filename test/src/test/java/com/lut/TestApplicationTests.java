package com.lut;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.FileDao;
import com.lut.dao.ScriptDao;
import com.lut.pojo.Chromosome;
import com.lut.pojo.Script;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    private ScriptDao scriptDao;
    @Autowired
    private FileDao fileDao;
    @SneakyThrows
    @Test
    void contextLoads() {
        ArrayList<Object> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        System.out.println(JSON.toJSONString(list));
    }
}
