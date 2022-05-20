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

    public String sendInsertVisualization(String msg){
        rabbitTemplate.convertAndSend(RabbitConfig.VISUALIZATION_EXCHANGE,RabbitConfig.VISUALIZATION_ROUTING_KEY,msg);
        return "ok";
    }

    public String sendInsertRequest(String msg){
        rabbitTemplate.convertAndSend(RabbitConfig.REQUEST_EXCHANGE,RabbitConfig.REQUEST_ROUTING_KEY,msg);
        return "ok";
    }

    public String sendProductRequest(String msg){
        rabbitTemplate.convertAndSend(RabbitConfig.PRODUCT_EXCHANGE,RabbitConfig.PRODUCT_ROUTING_KEY,msg);
        return "ok";
    }
}
