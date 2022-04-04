package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class File {
    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("url")
    private String url;
}
