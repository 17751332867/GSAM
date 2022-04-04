package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("indexing_benchmark_result")
@Data
public class IndexingBenchmarkResult {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("memory")
    private Long memory;
    @TableField("name")
    private String name;
    @TableField("realTime")
    private Double realTime;
    @TableField("cpu")
    private String cpu;
    @TableField("indexing_benchmark_id")
    private String indexingBenchmarkId;
    @TableField("res_file_id")
    private String resFileId;
    @TableField(exist = false)
    private File resFile;
}
