package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
@TableName("data")
public class Data {
    @TableId("id")
    private Integer id;
    @TableField("type")
    private String type;
    @TableField("size")
    private Long size;
    @TableField("description")
    private String description;
    @TableField("file_id")
    private String fileId;
    @TableField(exist = false)
    private List<File> fileList=new ArrayList<>();
}
