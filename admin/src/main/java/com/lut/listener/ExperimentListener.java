package com.lut.listener;

import com.lut.util.ProcessUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "experiment_queue")
public class ExperimentListener {
    @RabbitHandler
    public void doExperiment(String msg){
        System.out.println(msg);
        ProcessUtil.doProcess("D:\\workspace\\clion-workspace\\algorithm-learn\\suffix_auto_machine\\fifth_edition_fprintf_optimal");
    }
}
