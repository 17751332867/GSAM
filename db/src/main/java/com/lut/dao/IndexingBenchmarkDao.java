package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.File;
import com.lut.pojo.IndexingBenchmark;
import com.lut.pojo.IndexingBenchmarkTable;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndexingBenchmarkDao extends BaseMapper<IndexingBenchmark> {
    @Results(id = "indexingBenchmark",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "status", property = "status"),
                    @Result(column = "name", property = "name"),
                    @Result(column = "description", property = "description"),
                    @Result(column = "fa_file_id", property = "faFileId"),
                    @Result(column = "gfa_file_id", property = "gfaFileId"),
                    @Result(column = "create_date", property = "createDate"),
                    @Result(column = "fa_file_id", property = "faFile", javaType = File.class, one = @One(select = "com.lut.dao.FileDao.selectFilesById")),
                    @Result(column = "gfa_file_id", property = "gfaFile", javaType = File.class, one = @One(select = "com.lut.dao.FileDao.selectFilesById")),
                    @Result(column = "id", property = "indexingBenchmarkResults", javaType = List.class, many = @Many(select = "com.lut.dao.IndexingBenchmarkResultDao.selectByIndexingBenchmarkId"))
            })
    @Select("select * from indexing_benchmark where id = #{id}")
    IndexingBenchmark selectBenchmarkById(String id);

    @Results(id = "indexingBenchmarkTable",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "status",property = "status"),
                    @Result(column = "name",property = "name"),
                    @Result(column = "description",property = "description"),
                    @Result(column = "create_date",property = "createDate"),
                    @Result(column = "id",property = "algorithms",javaType = List.class,many = @Many(select = "com.lut.dao.IndexingBenchmarkResultDao.selectNameByBenchmarkId"))
            }
    )
    @Select("select * from indexing_benchmark where id in (select indexing_benchmark_id from user_indexing_benchmark where user_info_id = #{userId})")
    List<IndexingBenchmarkTable> selectBenchmarkByUserId(Integer userId);
}
