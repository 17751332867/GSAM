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
        String RES_DIR = BASE_DIR+SEPARATOR+"img"+SEPARATOR+"res";
        String PANGENOME_DIR = BASE_DIR+SEPARATOR+"img"+SEPARATOR+"pangenome";
        ensureFileExists(RES_DIR);
        ensureFileExists(DATA_DIR);
        ensureFileExists(INDEXING_DIR);
        ensureFileExists(FILE_DIR);
        ensureFileExists(PANGENOME_DIR);
        registry.addResourceHandler("/img/pangenome/**")
                .addResourceLocations("file:"+PANGENOME_DIR+SEPARATOR);
        registry.addResourceHandler("/img/data/**")
                .addResourceLocations("file:" + DATA_DIR+SEPARATOR);
        registry.addResourceHandler("/img/indexing/**")
                .addResourceLocations("file:"+INDEXING_DIR+SEPARATOR);
        registry.addResourceHandler("/img/file/**")
                .addResourceLocations("file:"+FILE_DIR+SEPARATOR);
        registry.addResourceHandler("/img/res/**")
                .addResourceLocations("file:"+RES_DIR+SEPARATOR);
    }
    public static void ensureFileExists(String dir){
         File file = new File(dir);
         if(!file.exists()){
             file.mkdirs();
         }
    }
}