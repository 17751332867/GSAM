package com.lut.listener;

import com.alibaba.fastjson.JSON;
import com.lut.pojo.dto.FileDto;
import com.lut.util.WriteFileUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "file_queue")
public class FileListener {
    @Autowired
    private WriteFileUtil writeFileUtil;
    @RabbitHandler
    public void doExperiment(String msg){
        FileDto fileDto = JSON.parseObject(msg, FileDto.class);
        System.out.println(fileDto);
//        writeFileUtil.ReadAndWriteTodb(fileDto);
//        ProcessUtil.doProcess("D:\\workspace\\clion-workspace\\algorithm-learn\\suffix_auto_machine\\fifth_edition_fprintf_optimal");
    }
}
