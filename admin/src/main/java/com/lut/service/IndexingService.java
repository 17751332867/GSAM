package com.lut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.IndexingDao;
import com.lut.pojo.Indexing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexingService {
    @Autowired
    private IndexingDao indexingDao;

    public List<Indexing> selectAll(){
        return indexingDao.selectList(new QueryWrapper<>());
    }

}
