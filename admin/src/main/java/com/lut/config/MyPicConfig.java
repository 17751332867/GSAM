package com.lut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        String BASE_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        String DATA_DIR = BASE_DIR+SEPARATOR+"img"+SEPARATOR+"data";
        ensureFileExists(DATA_DIR);

        registry.addResourceHandler("/img/data/**")
                .addResourceLocations("file:" + DATA_DIR);
    }
    public static void ensureFileExists(String dir){
         File file = new File(dir);
         if(!file.exists()){
             file.mkdir();
         }
    }
}