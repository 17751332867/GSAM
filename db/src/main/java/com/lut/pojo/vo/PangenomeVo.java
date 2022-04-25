package com.lut.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class PangenomeVo {

    private Integer userId;
    private List<Node> data;
    private List<Edge> links;
    private String name;
    private Integer num;
    private Integer size;
    private Integer maxLengthLowerBound;
    private Integer maxLengthUpperBound;
    private Integer minLengthLowerBound;
    private Integer minLengthUpperBound;

}
