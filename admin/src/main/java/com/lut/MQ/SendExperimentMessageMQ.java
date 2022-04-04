package com.lut.MQ;

import com.lut.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class SendExperimentMessageMQ {
    @Autowired
    RabbitTemplate rabbitTemplate;


    public String sendExperimentMessage(String msg){
        rabbitTemplate.convertAndSend(RabbitConfig.EXPERIMENT_EXCHANGE,RabbitConfig.EXPERIMENT_ROUTING_KEY,msg);
        return "ok";
    }

    public String sendIndexingBenchmarkMessage(String msg){
        rabbitTemplate.convertAndSend(RabbitConfig.INDEXING_BENCHMARK_EXCHANGE,RabbitConfig.INDEXING_BENCHMARK_ROUTING_KEY,msg);
        return "ok";
    }
}
