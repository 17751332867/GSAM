package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class File {
    @TableId("id")
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("url")
    private String url;
    @TableField("data_id")
    private Integer dataId;
}
