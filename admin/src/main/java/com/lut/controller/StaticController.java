package com.lut.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.pojo.TimeCount;
import com.lut.pojo.UrlCount;
import com.lut.pojo.vo.ResultData;
import com.lut.service.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("static")
public class StaticController {
    @Autowired
    private StaticService staticService;

    @RequestMapping("urlCountList")
    public ResultData<List<UrlCount>> getUrlCountList(){
        return new ResultData<List<UrlCount>>().success(staticService.getUrlCountList());
    }

    @RequestMapping("timeCountList")
    public ResultData<List<TimeCount>> getTimeCountList(){
        return new ResultData<List<TimeCount>>().success(staticService.getTimeCountList());
    }


}
