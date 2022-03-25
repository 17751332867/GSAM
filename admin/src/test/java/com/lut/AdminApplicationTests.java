package com.lut;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.config.ServerConfig;
import com.lut.dao.AdminDao;
import com.lut.feign.SendFileMessageController;
import com.lut.pojo.dto.FileDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminApplicationTests {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private SendFileMessageController sendFileMessageController;

    @Autowired
    ServerConfig serverConfig;
    @Value("${server.port}")
    private String serverPort;
    @Test
    void contextLoads() {
        System.out.println(serverConfig.getUrl());
    }

}
