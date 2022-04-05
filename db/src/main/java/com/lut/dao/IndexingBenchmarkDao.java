package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.IndexingBenchmark;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndexingBenchmarkDao extends BaseMapper<IndexingBenchmark> {
    @Results(id = "indexingBenchmark",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "status",property = "status"),
                    @Result(column = "name",property = "name"),
                    @Result(column = "description",property = "description"),
                    @Result(column = "fa_file_id",property = "faFileId"),
                    @Result(column = "gfa_file_id",property = "gfaFileId"),
                    @Result(column = "create_date",property = "createDate"),
                    @Result(column = "fa_file_id",property = "faFile",javaType = List.class,one = @One(select = "com.lut.dao.FileDao.selectFilesById")),
                    @Result(column = "gfa_file_id",property = "gfaFile",javaType = List.class,one = @One(select = "com.lut.dao.FileDao.selectFilesById"))
            })
    @Select("select * from indexing_benchmark where id in (select indexing_benchmark_id from user_indexing_benchmark where user_info_id = #{userId})")
    List<IndexingBenchmark> selectBenchmarkByUserId(Integer userId);
}
