package com.lut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.TimeCountDao;
import com.lut.dao.UrlCountDao;
import com.lut.pojo.Data;
import com.lut.pojo.TimeCount;
import com.lut.pojo.UrlCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaticService {
    @Autowired
    private UrlCountDao urlCountDao;
    @Autowired
    private TimeCountDao timeCountDao;

    public List<UrlCount> getUrlCountList(){
        return urlCountDao.selectList(new QueryWrapper<>());
    }

    public List<TimeCount> getTimeCountList(){
        long time = System.currentTimeMillis();
        time  = time - 24*3600*1000;
        Date date = new Date(time);
        String s = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate();
        TimeCount timeCount = new TimeCount();
        timeCount.setTime(s);
        List<TimeCount> list = timeCountDao.selectList(new QueryWrapper<>(timeCount));
        boolean used[] = new boolean[24];
        for(int i=0;i<list.size();i++){
            int h = list.get(i).getHour();
            used[h]= true;
        }
        for(int i=0;i<24;i++){
            if(used[i]==false){
                TimeCount tc = new TimeCount();
                tc.setTime(s);
                tc.setHour(i);
                tc.setCnt(0);
                list.add(tc);
            }
        }
        list.sort((t1,t2)->{
            return t1.getHour()-t2.getHour();
        });
        return list;
    }
}
