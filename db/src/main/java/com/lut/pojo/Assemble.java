package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("assemble")
public class Assemble {
    @TableId("id")
    private Integer id;
    @TableField("command")
    private String command;
    @TableField("file_id")
    private String fileId;
    @TableField("name")
    private String name;
    @TableField("description")
    private String description;
}
