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
    public static String INDEXING_BENCHMARK_QUEUE = "indexing_benchmark_queue";
    public static String INDEXING_BENCHMARK_EXCHANGE = "indexing_benchmark_exchange";
    public static String INDEXING_BENCHMARK_ROUTING_KEY = "indexing_benchmark_routing_key";
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
    @Bean("indexingBenchmarkQueue")
    public Queue indexingBenchmarkQueue(){
        return new Queue(INDEXING_BENCHMARK_QUEUE,true);
    }
    @Bean("indexingBenchmarkExchange")
    public DirectExchange indexingBenchmarkExchange(){
        return new DirectExchange(INDEXING_BENCHMARK_EXCHANGE,true,false);
    }
    @Bean("indexingBenchmarkBinding")
    public Binding indexingBenchmarkBinding(@Autowired Queue indexingBenchmarkQueue,@Autowired DirectExchange indexingBenchmarkExchange){
        return BindingBuilder.bind(indexingBenchmarkQueue).to(indexingBenchmarkExchange).with(INDEXING_BENCHMARK_ROUTING_KEY);
    }
}
