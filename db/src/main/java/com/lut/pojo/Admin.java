package com.lut.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("admin")
@Data
public class Admin {
    @TableId("id")
    private Integer id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("phone")
    private String phone;
}
