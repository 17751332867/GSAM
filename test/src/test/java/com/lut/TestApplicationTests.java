package com.lut;

import com.lut.dao.*;
import com.lut.pojo.IndexingExperiment;
import lombok.SneakyThrows;
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

    @Autowired
    UserIndexingExperimentDao userIndexingExperimentDao;

    @SneakyThrows
    @Test
    void contextLoads() {
        List<IndexingExperiment> experiments = indexingExperimentDao.selectIndexingExperimentByUserId(1);
        System.out.println(experiments);
    }
}
