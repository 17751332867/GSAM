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
@TableName("time_count")
public class TimeCount {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("time")
    private String time;
    @TableField("cnt")
    private Integer cnt;
    @TableField("hour")
    private Integer hour;
}
