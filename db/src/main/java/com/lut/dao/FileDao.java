package com.lut.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface FileDao extends BaseMapper<File> {
    @Select("select * from file where id = #{id}")
    public File selectFilesById(String id);
    @Select("select * from file where id = #{id}")
    public List<File> selectFileListById(String id);
}
