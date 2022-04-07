package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@TableName("indexing_benchmark")
@Data
public class IndexingBenchmark {
    @TableId(value = "id")
    private String  id;
    @TableField("status")
    private String status;
    @TableField("name")
    private String name;
    @TableField("description")
    private String description;
    @TableField("fa_file_id")
    private String faFileId;
    @TableField("gfa_file_id")
    private String gfaFileId;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("create_date")
    private Date createDate;
    @TableField(exist = false)
    private File faFile;
    @TableField(exist = false)
    private File gfaFile;
    @TableField(exist = false)
    private List<IndexingBenchmarkResult> indexingBenchmarkResults;

}
