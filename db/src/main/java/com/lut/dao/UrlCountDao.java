package com.lut.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.UrlCount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UrlCountDao extends BaseMapper<UrlCount> {
}
