package com.lut;

import com.lut.dao.*;
import com.lut.pojo.IndexingBenchmark;
import com.lut.pojo.IndexingExperiment;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    IndexingBenchmarkDao indexingBenchmarkDao;

    @SneakyThrows
    @Test
    void contextLoads() {
        List<IndexingBenchmark> benchmarks = indexingBenchmarkDao.selectBenchmarkByUserId(1);
    }
}
