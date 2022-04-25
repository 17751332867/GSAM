package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.PangenomeFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PangenomeFileDao extends BaseMapper<PangenomeFile> {
    @Select("select pangenome_file.id as id,name,graph_url as graphUrl,read_url as readUrl,res_url as resUrl,num,size,max_length_lower_bound as maxLengthLowerBound,max_length_upper_bound as maxLengthUpperBound,min_length_lower_bound as minLengthLowerBound,min_length_upper_bound as minLengthUpperBound from user_pangenome_file left join pangenome_file on pangenome_file.id=user_pangenome_file.pangenome_file_id where user_pangenome_file.user_info_id = #{id}")
    List<PangenomeFile> selectByUserId(Integer id);
}
