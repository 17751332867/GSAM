package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("visualization")
public class Visualization {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("input_url")
    private String inputUrl;
    @TableField("name")
    private String name;
    @TableField("description")
    private String description;
}
