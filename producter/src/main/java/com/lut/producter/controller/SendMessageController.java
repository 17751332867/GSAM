package com.lut.producter.controller;

import com.lut.producter.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendExperimentMessage")
    public String sendExperimentMessage(String msg){
        rabbitTemplate.convertAndSend(RabbitConfig.EXPERIMENT_EXCHANGE,RabbitConfig.EXPERIMENT_ROUTING_KEY,msg);
        return "ok";
    }
}
