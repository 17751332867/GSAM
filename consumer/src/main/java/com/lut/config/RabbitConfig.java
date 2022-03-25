package com.lut.config;

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
    public static String FILE_QUEUE = "file_queue";
    public static String FILE_EXCHANGE = "file_exchange";
    public static String FILE_ROUTING_KEY = "file_routing_key";
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
    @Bean("fileQueue")
    public Queue fileQueue(){
        return new Queue(FILE_QUEUE,true);
    }
    @Bean("fileExchange")
    public DirectExchange fileExchange(){
        return new DirectExchange(FILE_EXCHANGE,true,false);
    }
    @Bean("fileBinding")
    public Binding fileBinding(@Autowired Queue fileQueue,@Autowired DirectExchange fileExchange){
        return BindingBuilder.bind(fileQueue).to(fileExchange).with(FILE_ROUTING_KEY);
    }
}
