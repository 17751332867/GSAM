package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("visualization_img")
public class VisualizationImg {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("visualization_id")
    private Integer visualizationId;
    @TableField("img_url")
    private String imgUrl;
}
