package com.lut.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lut.dao.ChromosomeResDao;
import com.lut.dao.ScriptDao;
import com.lut.pojo.ChromosomeRes;
import com.lut.pojo.Script;
import com.lut.pojo.vo.ProductVo;
import com.lut.util.ProcessUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RabbitListener(queues = "product_queue")
public class ProductListener {

    @Autowired
    ScriptDao scriptDao;
    @Autowired
    ChromosomeResDao chromosomeResDao;
    @RabbitHandler
    public void resolveRequest(String msg){
        ProductVo productVo = JSON.parseObject(msg, ProductVo.class);
        ChromosomeRes chromosomeRes = chromosomeResDao.selectById(productVo.getId());
        try {
            chromosomeRes.setLength(productVo.getLength());
            chromosomeRes.setSize(productVo.getSize());
            chromosomeRes.setStatus("生成中");
            chromosomeResDao.updateById(chromosomeRes);
            Script script = scriptDao.selectOne(new QueryWrapper<Script>().eq("name", "get_fa"));
            String BASE_DIR = System.getProperty("user.dir");
            String SEPARATOR = System.getProperty("file.separator");
            String faPath = SEPARATOR+"img"+SEPARATOR+"chromosome"+SEPARATOR+ UUID.randomUUID()+"read"+productVo.getLength()+".fa";
            chromosomeRes.setFaUrl(faPath);
            String cmd = BASE_DIR+script.getUrl()+" "+BASE_DIR+chromosomeRes.getChromosomeUrl()+" "+productVo.getLength()+" "+productVo.getSize()+" > "+BASE_DIR+faPath;
            System.out.println(cmd);
            ProcessUtil.doProcess(cmd);
            chromosomeRes.setStatus("完成");
            chromosomeResDao.updateById(chromosomeRes);
        }
        catch(Exception e){
            try
            {
                chromosomeRes.setStatus("出错了");
                chromosomeResDao.updateById(chromosomeRes);
            }catch (Exception err){

            }
        }
    }
}
