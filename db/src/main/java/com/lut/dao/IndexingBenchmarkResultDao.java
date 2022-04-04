package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.IndexingBenchmark;
import com.lut.pojo.IndexingBenchmarkResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IndexingBenchmarkResultDao extends BaseMapper<IndexingBenchmarkResult> {
}
