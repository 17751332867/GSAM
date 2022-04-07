package com.lut;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.*;
import com.lut.pojo.IndexingBenchmark;
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
        indexingBenchmarkDao.selectList(new QueryWrapper<>());
        long begin = System.currentTimeMillis();
        IndexingBenchmark benchmarks = indexingBenchmarkDao.selectBenchmarkById("10b819a6-3c1b-4c99-af9c-d83788949ba5");
        System.out.println(benchmarks);
        System.out.println(System.currentTimeMillis()-begin);
    }
}
