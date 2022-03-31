package com.lut.dao;

import com.lut.pojo.File;
import com.lut.pojo.Indexing;
import com.lut.pojo.IndexingExperiment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndexingExperimentDao {

    @Results(id = "indexingExperment",value = {
        @Result(column = "id",property = "id"),
        @Result(column = "name",property = "name"),
        @Result(column = "status",property = "status"),
        @Result(column = "running_time",property = "runningTime"),
        @Result(column = "memory",property = "memory"),
        @Result(column = "indexing_id",property = "indexingId"),
        @Result(column = "fa_file_id",property = "faFileId"),
        @Result(column = "gfa_file_id",property = "gfaFileId"),
        @Result(column = "indexing_id",property = "indexing",javaType = Indexing.class,one=@One(select = "com.lut.dao.IndexingDao.selectById")),
        @Result(column = "gfa_file_id",property = "gfaFile",javaType = File.class,one = @One(select = "com.lut.dao.FileDao.selectFilesById")),
        @Result(column = "fa_file_id",property = "faFile",javaType = File.class,one = @One(select = "com.lut.dao.FileDao.selectFilesById"))
    })
    @Select("select * from indexing_experiment")
    List<IndexingExperiment> selectAll();
}
