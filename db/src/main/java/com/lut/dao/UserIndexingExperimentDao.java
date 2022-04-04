package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.UserIndexingExperiment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserIndexingExperimentDao extends BaseMapper<UserIndexingExperiment> {

}
