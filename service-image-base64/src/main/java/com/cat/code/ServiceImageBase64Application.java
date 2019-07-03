package com.cat.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.cat.code.mapper")
@ServletComponentScan
public class ServiceImageBase64Application {
    public static void main(String[] args){
        SpringApplication.run(ServiceImageBase64Application.class,args);
    }
}
