package com.lut.config;

import com.alibaba.druid.sql.visitor.functions.Bin;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static String REQUEST_QUEUE="request_queue";
    public static String REQUEST_EXCHANGE="request_exchange";
    public static String REQUEST_ROUTING_KEY="request_routing_key";
    public static String VISUALIZATION_QUEUE="visualization_queue";
    public static String VISUALIZATION_EXCHANGE="visualization_exchange";
    public static String VISUALIZATION_ROUTING_KEY="visualization_routing_key";
    public static String EXPERIMENT_QUEUE = "experiment_queue";
    public static String EXPERIMENT_EXCHANGE = "experiment_exchange";
    public static String EXPERIMENT_ROUTING_KEY = "experiment_routing_key";
    public static String INDEXING_BENCHMARK_QUEUE = "indexing_benchmark_queue";
    public static String INDEXING_BENCHMARK_EXCHANGE = "indexing_benchmark_exchange";
    public static String INDEXING_BENCHMARK_ROUTING_KEY = "indexing_benchmark_routing_key";
    public static String PRODUCT_QUEUE = "product_queue";
    public static String PRODUCT_EXCHANGE = "product_exchange";
    public static String PRODUCT_ROUTING_KEY = "product_routing_key";
    @Bean("requestQueue")
    public Queue requestQueue(){return new Queue(REQUEST_QUEUE,true);}
    @Bean("requestExchange")
    public DirectExchange requestExchange(){return new DirectExchange(REQUEST_EXCHANGE,true,false);}
    @Bean("requestBinding")
    public Binding requestBinding(@Autowired Queue requestQueue,@Autowired DirectExchange requestExchange){
        return BindingBuilder.bind(requestQueue).to(requestExchange).with(REQUEST_ROUTING_KEY);
    }
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
    @Bean("visualizationQueue")
    public Queue visualizationQueue(){
        return new Queue(VISUALIZATION_QUEUE,true);
    }
    @Bean("visualizationExchange")
    public DirectExchange visualizationExchange(){
        return new DirectExchange(VISUALIZATION_EXCHANGE,true,false);
    }
    @Bean("visualizationBinding")
    public Binding visualizationBinding(@Autowired Queue visualizationQueue,@Autowired DirectExchange visualizationExchange){
        return BindingBuilder.bind(visualizationQueue).to(visualizationExchange).with(VISUALIZATION_ROUTING_KEY);
    }
    @Bean("productQueue")
    public Queue productQueue(){
        return new Queue(PRODUCT_QUEUE,true);
    }
    @Bean("productExchange")
    public DirectExchange productExchange(){
        return new DirectExchange(PRODUCT_EXCHANGE,true,false);
    }
    @Bean("productBinding")
    public Binding productBinding(@Autowired Queue productQueue,@Autowired DirectExchange productExchange){
        return BindingBuilder.bind(productQueue).to(productExchange).with(PRODUCT_ROUTING_KEY);
    }

}
