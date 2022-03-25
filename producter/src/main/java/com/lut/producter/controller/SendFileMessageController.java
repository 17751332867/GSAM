package com.lut.producter.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.lut.pojo.dto.FileDto;
import com.lut.producter.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SendFileMessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendFileMessage")
    public String sendFileMessage(@RequestBody FileDto fileDto){
        String jsonString = JSON.toJSONString(fileDto);
        rabbitTemplate.convertAndSend(RabbitConfig.FILE_EXCHANGE,RabbitConfig.FILE_ROUTING_KEY,jsonString);
        return "ok";
    }
}
