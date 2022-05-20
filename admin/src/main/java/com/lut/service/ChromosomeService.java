package com.lut.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lut.MQ.SendExperimentMessageMQ;
import com.lut.dao.ChromosomeResDao;
import com.lut.dao.FileDao;
import com.lut.dao.ScriptDao;
import com.lut.pojo.Chromosome;
import com.lut.pojo.ChromosomeRes;
import com.lut.pojo.File;
import com.lut.pojo.Script;
import com.lut.pojo.vo.ChromosomeVo;
import com.lut.pojo.vo.ProductVo;
import com.lut.service.inter.IChromosomeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

@Service
public class ChromosomeService extends ServiceImpl<ChromosomeResDao,ChromosomeRes> implements IChromosomeService {

    @Autowired
    ScriptDao scriptDao;
    @Autowired
    FileDao fileDao;
    @Autowired
    SendExperimentMessageMQ sendExperimentMessageMQ;
    @Autowired
    ChromosomeResDao chromosomeResDao;
    @SneakyThrows
    public List<Chromosome> scanFile(String id){
        File file = fileDao.selectById(id);
        Script script = new Script();
        script.setName("get_dna");
        script = scriptDao.selectOne(new QueryWrapper<>(script));
        String BASE_DIR = System.getProperty("user.dir");
        String cmd = BASE_DIR+script.getUrl()+" "+BASE_DIR+file.getUrl();
        String [] cmds={"/bin/sh","-c",cmd};
        Process exec = null;
        try {
            exec = Runtime.getRuntime().exec(cmds);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner cin = new Scanner(exec.getInputStream());
        String str = "";
        while(cin.hasNextLine()){
            str+=cin.nextLine();
        }
        List<Chromosome> list = JSON.parseArray(str, Chromosome.class);
        return list;
    }


    public void insertChromosomes(ChromosomeVo chromosomeVo) {
        File file = fileDao.selectById(chromosomeVo.getFileId());
        ChromosomeRes chromosomeRes = new ChromosomeRes();
        List<ChromosomeRes> chromosomeResList = new ArrayList<>();
        List<Chromosome> list = chromosomeVo.getList();
        for (int i = 0; i < list.size(); i++) {
            chromosomeRes = new ChromosomeRes();
            chromosomeRes.setChromosomeUrl(list.get(i).getUrl());
            chromosomeRes.setCycle(list.get(i).getCycle());
            chromosomeRes.setName(file.getName()+"_"+list.get(i).getDna());
            chromosomeRes.setCreateTime(new Date(System.currentTimeMillis()+8*3600*1000));
            String BASE_DIR = System.getProperty("user.dir");
            String SEPARATOR = System.getProperty("file.separator");
            java.io.File f = new java.io.File(BASE_DIR+list.get(i).getUrl());
            chromosomeRes.setFileSize(f.length());
            chromosomeRes.setOriginName(file.getName());
            chromosomeRes.setUserId(chromosomeVo.getUserId());
            chromosomeRes.setOriginUrl(file.getUrl());
            chromosomeRes.setTot(list.get(i).getTot());
            chromosomeRes.setStatus("未生成序列");
            chromosomeResList.add(chromosomeRes);
        }
        System.out.println(chromosomeResList);
        saveBatch(chromosomeResList);
    }

    public List<ChromosomeRes> selectChromosomesByUserId(Integer id) {
        ChromosomeRes chromosomeRes = new ChromosomeRes();
        QueryWrapper<ChromosomeRes> wrapper = new QueryWrapper<ChromosomeRes>().eq("user_info_id", id);
        List<ChromosomeRes> list = chromosomeResDao.selectList(wrapper);
        return list;
    }

    public void productFa(ProductVo productVo) {
        System.out.println(productVo);
        ChromosomeRes chromosomeRes = chromosomeResDao.selectById(productVo.getId());
        System.out.println(chromosomeRes);
        chromosomeRes.setStatus("排队中");
        chromosomeResDao.updateById(chromosomeRes);
        sendExperimentMessageMQ.sendProductRequest(JSON.toJSONString(productVo));
        selectChromosomesByUserId(productVo.getUserId());
    }

    public List<ChromosomeRes> selectAll() {
        return chromosomeResDao.selectList(new QueryWrapper<>());
    }

    public List<ChromosomeRes> deleteById(Integer id) {
        ChromosomeRes chromosomeRes = chromosomeResDao.selectById(id);
        String BASE_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        String s = BASE_DIR + SEPARATOR + chromosomeRes.getFaUrl();
        java.io.File file = new java.io.File(s);
        file.delete();
        s = BASE_DIR+SEPARATOR+chromosomeRes.getChromosomeUrl();
        file = new java.io.File(s);
        file.delete();
        int res = chromosomeResDao.deleteById(id);
        return chromosomeResDao.selectList(new QueryWrapper<>());
    }
}
