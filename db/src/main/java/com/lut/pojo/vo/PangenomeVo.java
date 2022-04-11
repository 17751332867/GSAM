package com.lut.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class PangenomeVo {
    @Data
    public class Node{
        String name;
    }
    @Data
    public class Edge{
        String source;
        String target;
        String name;
    }
    private Long userId;
    private List<Node> data;
    private List<Edge> links;
    private Integer num;
    private Integer sime;
    private Integer maxLengthLowerBound;
    private Integer maxLengthUpperBound;
    private Integer minLengthLowerBound;
    private Integer minLengthUpperBound;

}
