package com.lut.controller;

import com.lut.pojo.vo.ResultData;
import com.lut.pojo.vo.VisualizationVo;
import com.lut.service.VisualizationService;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("visualization")
public class VisualizationController {
    @Autowired
    private VisualizationService visualizationService;


    @RequestMapping("upload")
    public ResultData<String> upload(Integer id, MultipartFile file){
        String res = visualizationService.upload(file);
        if(!res.equals("failed"))
            return new ResultData<String>().success(res);
        else
            return new ResultData<String>().error("failed");
    }
    @RequestMapping("visualize")
    public ResultData<List<String>> visualize(@RequestBody VisualizationVo visualizationVo){
        List<String> list = visualizationService.visualize(visualizationVo.getId(),visualizationVo.getUserId());
        return new ResultData<List<String>>().success(list);
    }
}
