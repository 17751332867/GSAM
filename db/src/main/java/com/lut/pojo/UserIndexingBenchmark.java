package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user_indexing_benchmark")
@Data
public class UserIndexingBenchmark {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("user_info_id")
    private Integer userId;
    @TableField("indexing_benchmark_id")
    private String indexingBenchmarkId;
}
