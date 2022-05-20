package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_visualization")
public class UserVisualization {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("user_info_id")
    private Integer userId;
    @TableField("visualization_id")
    private Integer visualizationId;
}
