package com.lut.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lut.pojo.Chromosome;
import com.lut.pojo.ChromosomeRes;
import com.lut.pojo.vo.ChromosomeVo;
import com.lut.pojo.vo.ProductVo;
import com.lut.pojo.vo.ResultData;
import com.lut.service.ChromosomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("chromosome")
public class ChromosomeController {
    @Autowired
    private ChromosomeService chromosomeService;

    @RequestMapping("scanFile")
    private ResultData<List<Chromosome>> scanFile(String id){
        List<Chromosome> list = chromosomeService.scanFile(id);
        return new ResultData<List<Chromosome>>().success(list);
    }

    @RequestMapping("insertChromosomes")
    private ResultData<String> insertChromosomes(@RequestBody ChromosomeVo chromosomeVo){
        chromosomeService.insertChromosomes(chromosomeVo);
        return new ResultData<String>().success("success");
    }

    @RequestMapping("selectChromosomesByUserId")
    private ResultData<List<ChromosomeRes>> selectChromosomesByUserId(Integer id){
        List<ChromosomeRes> list = chromosomeService.selectChromosomesByUserId(id);
        return new ResultData<List<ChromosomeRes>>().success(list);
    }

    @RequestMapping("productFa")
    private ResultData<String> productFa(ProductVo productVo){
        chromosomeService.productFa(productVo);
        return new ResultData<String>().success("success");
    }

    @RequestMapping("selectAll")
    public ResultData<List<ChromosomeRes>> selectAll(){
        List<ChromosomeRes> list = chromosomeService.selectAll();
        return new ResultData<List<ChromosomeRes>>().success(list);
    }

    @RequestMapping("delete")
    public ResultData<List<ChromosomeRes>> delete(Integer id){
        List<ChromosomeRes> list = chromosomeService.deleteById(id);
        return new ResultData<List<ChromosomeRes>>().success(list);
    }
}
