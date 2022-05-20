package com.lut.pojo.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public  class Temp{
    private String id;
    private List<Integer> list;
}