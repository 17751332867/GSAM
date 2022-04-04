package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.Indexing;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndexingDao extends BaseMapper<Indexing> {
    @Results(id="indexingMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "command",property = "command"),
            @Result(column = "name",property = "name"),
            @Result(column = "description",property = "description"),
            @Result(column = "file_id",property = "fileId"),
            @Result(column = "file_id",property = "fileList",javaType = List.class,many = @Many(select = "com.lut.dao.FileDao.selectFilesById"))
    })
    @Select("select * from indexing")
    List<Indexing> selectAll();
    @ResultMap(value = "indexingMap")
    @Select("select * from indexing where id = #{id}")
    List<Indexing> selectById(Indexing id);
}
