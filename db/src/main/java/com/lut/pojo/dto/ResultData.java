package com.lut.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.NewExpr;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> {
    public static Integer SUCCESS = 1;
    public static Integer ERROR = 2;
    private T data;
    private String msg;
    private Integer status;
    public ResultData<T> success(String msg,T data){
        return new ResultData<T>(data,msg,SUCCESS);
    }
    public ResultData<T> success(T data){
        return new ResultData<T>(data,"success",SUCCESS);
    }
    public ResultData<T> error(String msg,T data){
        return  new  ResultData<T>(data,msg,ERROR);
    }
    public ResultData<T> error(T data){
        return  new  ResultData<T>(data,"error",ERROR);
    }
}
