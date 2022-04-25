package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.Data;
import com.lut.pojo.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DataDao extends BaseMapper<Data> {
    @Results(id="dataMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "type",property = "type"),
            @Result(column = "name",property = "name"),
            @Result(column = "size",property = "size"),
            @Result(column = "description",property = "description"),
            @Result(column = "file_id",property = "fileList",javaType = File.class,one = @One(select = "com.lut.dao.FileDao.selectFilesById"))
    })
    @Select("select * from data")
    public List<Data> selectAll();

}
