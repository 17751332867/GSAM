package com.lut.feign;

import com.lut.pojo.dto.FileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@FeignClient(name="producter")
public interface SendFileMessageController {


    @RequestMapping("/sendFileMessage")
    String sendFileMessage(@RequestBody FileDto fileDto);
}