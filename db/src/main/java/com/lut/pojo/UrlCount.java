package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("url_count")
public class UrlCount {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("url")
    private String url;
    @TableField("cnt")
    private Integer cnt;
}
