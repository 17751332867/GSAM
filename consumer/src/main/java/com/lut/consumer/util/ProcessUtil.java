package com.lut.consumer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessUtil {
    public static void doProcess(String cmd){
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
