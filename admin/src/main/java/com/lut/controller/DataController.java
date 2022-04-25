package com.lut.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lut.pojo.Data;
import com.lut.pojo.vo.DataVo;
import com.lut.pojo.vo.ResultData;
import com.lut.service.DataService;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("data")
public class DataController {

    @Autowired
    private DataService dataService;

    @RequestMapping("delete")
    public ResultData<List<Data>> delete(Integer id){
        List<Data> list = dataService.delete(id);
        return new ResultData<List<Data>>().success(list);
    }

    @PostMapping("update")
    public ResultData<List<Data>> update(@RequestBody Data data){
//        System.out.println(data);
        List<Data> list = dataService.update(data);
        return new ResultData<List<Data>>().success(list);
    }

    @RequestMapping("upload")
    public ResultData<String> upload(Integer id, MultipartFile file){
        String res = dataService.upload(id, file);
        if(res.equals("success"))
            return new ResultData<String>().success("ok");
        else
            return new ResultData<String>().error("failed");
    }

    @PostMapping(value = "insert")
    public ResultData<List<Data>> insert(@RequestBody DataVo dataVo){
//        System.out.println(dataVo);
        List<Data> list = dataService.insert(dataVo);
        return new ResultData<List<Data>>().success(list);
    }

    @RequestMapping("selectAll")
    public ResultData<List<Data>> selectAll(){
        List<Data> data = dataService.getAllData();
        return new ResultData<List<Data>>().success(data);
    }

    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response){
        String fileurl = request.getParameter("file");
        String BASE_DIR = System.getProperty("user.dir");
        File file = new File(BASE_DIR+fileurl);//读取压缩文件
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            int totalSize = inputStream.available(); //获取文件大小
            System.out.println("压缩后===当前文件下载大小size="+totalSize);
            response.setHeader("Content-Length", totalSize+"");//这里注意 一定要在response.getOutputStream()之前就把这个setHeader属性设进去，否则也不生效
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileurl,"UTF-8"));
            FileInputStream in = new FileInputStream(file);
            // 5. 创建缓冲区
            int len = 0;
            byte[] buffer = new byte[1024*1024];
            // 6. 获取OutputStream对象
            ServletOutputStream out = response.getOutputStream();
            while ((len=in.read(buffer))>0){
                out.write(buffer,0,len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
