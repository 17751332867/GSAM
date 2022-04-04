package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_indexing_experiment")
public class UserIndexingExperiment {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_info_id")
    private Integer userInfoId;
    @TableField(value = "indexing_experiment_id")
    private String indexingExperimentId;
}
