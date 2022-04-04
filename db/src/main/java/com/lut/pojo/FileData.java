package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("file_data")
@Data
public class FileData {
    @TableId("id")
    private String id;
    @TableField("file_data")
    private byte[] fileData;
    @TableField("file_id")
    private String fileId;
}
