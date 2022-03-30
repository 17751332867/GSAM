package com.lut;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.AssembleDao;
import com.lut.dao.DataDao;
import com.lut.dao.FileDataDao;
import com.lut.dao.IndexingExperimentDao;
import com.lut.pojo.Assemble;
import com.lut.pojo.IndexingExperiment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    AssembleDao assembleDao;

    @Autowired
    IndexingExperimentDao indexingExperimentDao;
    @Test
    void contextLoads() {
        List<IndexingExperiment> indexingExperiments = indexingExperimentDao.selectAll();
        System.out.println(indexingExperiments);
    }

}
