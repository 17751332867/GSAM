package com.lut.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lut.pojo.Indexing;
import com.lut.pojo.dto.ResultData;
import com.lut.service.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("indexing")
public class IndexingController {

    @Autowired
    private IndexingService indexingService;

    @RequestMapping("update")
    public ResultData<List<Indexing>> update(@RequestBody Indexing indexing){
        List<Indexing> indexings = indexingService.upload(indexing);
        return new ResultData<List<Indexing>>().success(indexings);
    }

    @RequestMapping("delete")
    public ResultData<List<Indexing>> delete(Integer id){
        List<Indexing> indexings = indexingService.delete(id);
        return new ResultData<List<Indexing>>().success(indexings);
    }

    @RequestMapping("insert")
    public ResultData<List<Indexing>> insert(@RequestBody Indexing indexing){
        List<Indexing> indexings = indexingService.insert(indexing);
        return new ResultData<List<Indexing>>().success(indexings);
    }

    @RequestMapping("selectAll")
    public ResultData<List<Indexing>> selectAll(){
        List<Indexing> indexings = indexingService.selectAll();
        return new ResultData<List<Indexing>>().success(indexings);
    }

    @RequestMapping("upload")
    public ResultData<String> upload(Integer id, MultipartFile file){
        System.out.println(id);
        System.out.println(file);
        String res = indexingService.upload(id, file);
        if(res.equals("success"))
            return new ResultData<String>().success("ok");
        else
            return new ResultData<String>().error("failed");
    }

}
