package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("algorithm")
public class Algorithm {
    @TableField("id")
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("file_name")
    private String fileName;
    @TableField("file")
    private byte[] file;
    @TableField("description")
    private String description;
    @TableField("type")
    private String type;
    @TableField("command")
    private String command;
}
