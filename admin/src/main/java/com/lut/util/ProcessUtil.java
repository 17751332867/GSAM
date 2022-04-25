package com.lut.util;

import com.alibaba.fastjson.JSON;
import com.lut.pojo.vo.MemoryInfo;
import com.lut.websocket.MessageWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProcessUtil {
    @Autowired
    MessageWebSocket messageWebSocket;
    public static MemoryInfo doProcess(String cmd){
        MemoryInfo memoryInfo = null;
        try {
            String [] cmds={"/bin/sh","-c",cmd};
            Process p = Runtime.getRuntime().exec(cmds);
            p.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            String regex = "\\{([^}])*\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sb.toString());
            while (matcher.find()) {
                line = matcher.group();
            }
            System.out.println(line);
            memoryInfo = JSON.parseObject(line, MemoryInfo.class);
            System.out.println(memoryInfo);
            return memoryInfo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return memoryInfo;
    }
    public MemoryInfo doProcessWithOutputStream(String cmd,Integer userId){
        Session session = messageWebSocket.getSocket(userId);
        System.out.println(session);
        MemoryInfo memoryInfo = null;
        try {
            String [] cmds={"/bin/sh","-c",cmd};
            Process p = Runtime.getRuntime().exec(cmds);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line=br.readLine())!=null){
                messageWebSocket.sendMessage(line,session);
//                System.out.println(line);
            }
            p.waitFor();
            br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            line = null;
            sb = new StringBuffer();
            while((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            String regex = "\\{([^}])*\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sb.toString());
            while (matcher.find()) {
                line = matcher.group();
            }
            System.out.println(line);
            memoryInfo = JSON.parseObject(line, MemoryInfo.class);
            System.out.println(memoryInfo);
            return memoryInfo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return memoryInfo;
    }
}
