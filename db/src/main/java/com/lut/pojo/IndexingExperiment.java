package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("indexing_experiment")
public class IndexingExperiment {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("indexing_id")
    private Integer indexingId;
    @TableField("name")
    private String name;
    @TableField("fa_file_id")
    private String faFileId;
    @TableField("gfa_file_id")
    private String gfaFileId;
    @TableField("status")
    private String status;
    @TableField("running_time")
    private Long runningTime;
    @TableField("memory")
    private Long memory;
    @TableField(exist = false)
    private Indexing indexing;
    @TableField(exist = false)
    private File faFile;
    @TableField(exist = false)
    private File gfaFile;
}
