package com.lut.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class IndexingBenchmarkVo {
    private String name;
    private String description;
    private Integer userId;
    private String faFile;
    private String gfaFile;
    private List<Integer> algorithms;
    private String indexingBenchmarkId;
}
