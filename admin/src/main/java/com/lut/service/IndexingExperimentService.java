package com.lut.service;

import com.lut.dao.IndexingDao;
import com.lut.dao.IndexingExperimentDao;
import com.lut.pojo.Indexing;
import com.lut.pojo.IndexingExperiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexingExperimentService {
    @Autowired
    private IndexingExperimentDao indexingExperimentDao;

    public List<IndexingExperiment> selectAll(){
        return indexingExperimentDao.selectAll();
    }
}
