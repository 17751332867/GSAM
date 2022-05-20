package com.lut.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class VisualizationSaveVo {
    private Integer userId;
    private String name;
    private String description;
    private String inputId;
    private List<String> outputList;
}
