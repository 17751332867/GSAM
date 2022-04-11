package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pangenome_file")
public class PangenomeFile {
    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("graph_url")
    private String graphUrl;
    @TableField("read_url")
    private String readUrl;
    @TableField("res_url")
    private String resUrl;
    @TableField("num")
    private Integer num;
    @TableField("size")
    private Integer size;
    @TableField("max_length_lower_bound")
    private Integer maxLengthLowerBound;
    @TableField("max_length_upper_bound")
    private Integer maxLengthUpperBound;
    @TableField("min_length_lower_bound")
    private Integer minLengthLowerBound;
    @TableField("min_length_upper_bound")
    private Integer minLengthUpperBound;
}
