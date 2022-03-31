package com.lut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String BASE_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        String DATA_DIR = BASE_DIR+SEPARATOR+"img"+SEPARATOR+"data";
        String INDEXING_DIR = BASE_DIR+SEPARATOR+"img"+SEPARATOR+"indexing";
        String FILE_DIR = BASE_DIR+SEPARATOR+"img"+SEPARATOR+"file";
        ensureFileExists(DATA_DIR);
        ensureFileExists(INDEXING_DIR);
        ensureFileExists(FILE_DIR);
        registry.addResourceHandler("/img/data/**")
                .addResourceLocations("file:" + DATA_DIR+SEPARATOR);
        registry.addResourceHandler("/img/indexing/**")
                .addResourceLocations("file:"+INDEXING_DIR+SEPARATOR);
        registry.addResourceHandler("/img/file/**")
                .addResourceLocations("file:"+INDEXING_DIR+SEPARATOR);
    }
    public static void ensureFileExists(String dir){
         File file = new File(dir);
         if(!file.exists()){
             file.mkdirs();
         }
    }
}