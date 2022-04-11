package com.lut.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lut.pojo.UserPangenomeFile;

@TableName("user_pangenome_file")
public interface UserPangenomeFileDao extends BaseMapper<UserPangenomeFile> {

}
