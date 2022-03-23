package com.lut.dao;

import com.lut.pojo.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DataDao {
    @Results(id="dataMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "type",property = "type"),
            @Result(column = "size",property = "size"),
            @Result(column = "description",property = "description"),
            @Result(column = "id",property = "fileList",javaType = List.class,many = @Many(select = "com.lut.dao.FileDao.selectFileByDataId"))
    })
    @Select("select * from data")
    public List<Data> selectAll();
}
