package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_pangenome_file")
public class UserPangenomeFile {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("user_info_id")
    private Integer userId;
    @TableField("pangenome_file_id")
    private Integer pangenomeFileId;
}
