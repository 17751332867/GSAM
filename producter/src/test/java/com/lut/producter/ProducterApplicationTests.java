package com.lut.producter;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class ProducterApplicationTests {

    @Autowired
    RestTemplate restTemplate;
    @SneakyThrows
    @Test
    void contextLoads() {

        ResponseEntity<byte[]> responseEntity = restTemplate.exchange("http://10.198.30.201:8000/img/data/fb3a938d-97f3-4412-b407-b5612677c2b9AddBookServlet_doPost.png", HttpMethod.GET, null, byte[].class);
        byte[] bytes = responseEntity.getBody();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/aaa.png"));
        bos.write(bytes);
        bos.flush();
        bos.close();
    }

}
