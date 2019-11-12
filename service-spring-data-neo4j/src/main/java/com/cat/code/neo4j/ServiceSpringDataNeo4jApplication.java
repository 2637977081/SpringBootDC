package com.cat.code.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @Author: lvgang
 * @Time: 2019/11/11 14:46
 * @Email: lvgang@golaxy.cn
 * @Description: 启动类
 */
@SpringBootApplication
@EnableNeo4jRepositories
public class ServiceSpringDataNeo4jApplication {

    public static void main(String[] args){
        SpringApplication.run(ServiceSpringDataNeo4jApplication.class,args);
    }
}
