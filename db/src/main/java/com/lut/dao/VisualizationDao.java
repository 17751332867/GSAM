package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.Visualization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VisualizationDao extends BaseMapper<Visualization> {
    @Select("select visualization.id as id,name,description,input_url as inputUrl from visualization left join user_visualization uv on visualization.id = uv.visualization_id where uv.user_info_id = #{id}")
    List<Visualization> selectByUserId(Integer userId);
}
