package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.IndexingBenchmark;
import com.lut.pojo.IndexingBenchmarkResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndexingBenchmarkResultDao extends BaseMapper<IndexingBenchmarkResult> {
    @Results(id="indexingBenchmarkResult",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "memory",property = "memory"),
            @Result(column = "name",property = "name"),
            @Result(column = "realTime",property = "realTime"),
            @Result(column = "cpu",property = "cpu"),
            @Result(column = "indexing_benchmark_id",property = "indexingBenchmarkId"),
            @Result(column = "res_file_id",property = "resFileId"),
            @Result(column = "res_file_id",property = "resFile",javaType = List.class,one = @One(select = "com.lut.dao.FileDao.selectFilesById"))
    })
    @Select("select * from indexing_benchmark_result where indexing_benchmark_id = #{id}")
    public List<IndexingBenchmarkResult> selectByIndexingBenchmarkId(String id);

}
