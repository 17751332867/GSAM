package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("indexing_experiment")
public class IndexingExperiment {
    @TableId(value = "id")
    private String id;
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
    private Double runningTime;
    @TableField("memory")
    private Long memory;
    @TableField("description")
    private String description;
    @TableField(exist = false)
    private Indexing indexing;
    @TableField(exist = false)
    private File faFile;
    @TableField(exist = false)
    private File gfaFile;
    @TableField("res_file_id")
    private String resFileId;
    @TableField(exist = false)
    private File resFile;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("create_date")
    private Date createDate;
}
