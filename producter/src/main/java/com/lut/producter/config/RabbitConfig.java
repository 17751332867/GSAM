package com.lut.producter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static String EXPERIMENT_QUEUE = "experiment_queue";
    public static String EXPERIMENT_EXCHANGE = "experiment_exchange";
    public static String EXPERIMENT_ROUTING_KEY = "experiment_routing_key";
    @Bean("experimentQueue")
    public Queue experimentQueue(){
        return new Queue(EXPERIMENT_QUEUE,true);
    }
    @Bean("experimentExchange")
    public DirectExchange experimentExchange(){
        return new DirectExchange(EXPERIMENT_EXCHANGE,true,false);
    }
    @Bean("experimentBinding")
    public Binding experimentBinding(@Autowired Queue experimentQueue,@Autowired DirectExchange experimentExchange){
        return BindingBuilder.bind(experimentQueue).to(experimentExchange).with(EXPERIMENT_ROUTING_KEY);
    }
}
