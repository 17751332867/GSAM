package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@TableName("indexing")
@Data
public class Indexing {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("command")
    private String command;
    @TableField("file_id")
    private String fileId;
    @TableField("name")
    private String name;
    @TableField("description")
    private String description;
    @TableField(exist = false)
    private List<File> fileList=new ArrayList<>();
}
