package com.lut.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.TimeCountDao;
import com.lut.dao.UrlCountDao;
import com.lut.pojo.TimeCount;
import com.lut.pojo.UrlCount;
import com.lut.pojo.vo.Static;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RabbitListener(queues = "request_queue")
public class RequestListener {

    @Autowired
    UrlCountDao urlCountDao;
    @Autowired
    TimeCountDao timeCountDao;

    @RabbitHandler
    public void resolveRequest(String msg){
        Static aStatic = JSON.parseObject(msg, Static.class);
        String url = aStatic.getUrl();
        UrlCount urlCount = new UrlCount();
        urlCount.setUrl(url);
        try{
            UrlCount count = urlCountDao.selectOne(new QueryWrapper<UrlCount>(urlCount));
//        System.out.println(count);
            if(count==null){
                urlCount.setCnt(1);
                urlCountDao.insert(urlCount);
            }else{
                count.setCnt(count.getCnt()+1);
                urlCountDao.updateById(count);
            }
            Date date = aStatic.getDate();
            String ss = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate());
            TimeCount timeCount = new TimeCount();
            timeCount.setTime(ss);
            timeCount.setHour(date.getHours());
            TimeCount one = timeCountDao.selectOne(new QueryWrapper<>(timeCount));
//        System.out.println(one);
            if(one==null){
                timeCount.setCnt(1);
                timeCountDao.insert(timeCount);
                timeCount.setHour(date.getHours());
            }else{
                one.setCnt(one.getCnt()+1);
                timeCountDao.updateById(one);
            }
        }catch (Exception e){
            System.out.println("出错了");
        }
    }
}
