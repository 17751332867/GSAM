package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.File;
import com.lut.pojo.Indexing;
import com.lut.pojo.IndexingExperiment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndexingExperimentDao extends BaseMapper<IndexingExperiment> {

    @Results(id = "indexingExperiment",value = {
        @Result(column = "id",property = "id"),
        @Result(column = "name",property = "name"),
        @Result(column = "status",property = "status"),
        @Result(column = "running_time",property = "runningTime"),
        @Result(column = "memory",property = "memory"),
        @Result(column = "indexing_id",property = "indexingId"),
        @Result(column = "fa_file_id",property = "faFileId"),
        @Result(column = "gfa_file_id",property = "gfaFileId"),
        @Result(column = "res_file_id",property = "resFileId"),
        @Result(column = "indexing_id",property = "indexing",javaType = Indexing.class,one=@One(select = "com.lut.dao.IndexingDao.selectById")),
        @Result(column = "gfa_file_id",property = "gfaFile",javaType = File.class,one = @One(select = "com.lut.dao.FileDao.selectFilesById")),
        @Result(column = "fa_file_id",property = "faFile",javaType = File.class,one = @One(select = "com.lut.dao.FileDao.selectFilesById")),
        @Result(column = "res_file_id",property = "resFile",javaType = File.class,one = @One(select = "com.lut.dao.FileDao.selectFilesById"))
    })
    @Select("select * from indexing_experiment")
    List<IndexingExperiment> selectAll();

    @ResultMap(value = "indexingExperiment")
    @Select("select * from indexing_experiment where id in (select indexing_experiment_id from user_indexing_experiment where user_info_id = #{userId})")
    List<IndexingExperiment> selectIndexingExperimentByUserId(Integer userId);
}
