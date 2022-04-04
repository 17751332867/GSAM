package com.lut.pojo.vo;

import lombok.Data;

@Data
public class ExperimentVo {
    private Integer userId;
    private String name;
    private String description;
    private String faFile;
    private String gfaFile;
    private Integer algorithmId;
}
