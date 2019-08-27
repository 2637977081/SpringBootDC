package com.cat.code.config.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: lvgang
 * @Time: 2019/8/15 15:52
 * @Description: 第一数据库配置
 */
@Configuration
//前缀为spring.datasource.mysql的配置信息
@MapperScan(basePackages = "com.cat.code.mappers.primary", sqlSessionFactoryRef = "primaryMysqlSessionFactory")
public class PrimaryMysqlDataSourceConfig {
    
    // 主数据源使用@Primary注解进行标识
    @Primary
    @Bean(name = "primaryMysqlDataSource", destroyMethod = "close",initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.primary") // 将所有前缀为spring.datasource.mysql下的配置项都加载到DataSource中
    public DataSource primaryMysqlDataSource() {
        return new DruidDataSource();
    }

    // 创建该数据源的事务管理
    @Primary
    @Bean(name = "primaryMysqlTransactionManager")
    public DataSourceTransactionManager primaryMysqlTransactionManager() {
        return new DataSourceTransactionManager(primaryMysqlDataSource());
    }

    // 创建Mybatis的连接会话工厂实例
    @Primary
    @Bean(name = "primaryMysqlSessionFactory")
    public SqlSessionFactory primaryMysqlSessionFactory(@Qualifier("primaryMysqlDataSource") DataSource mysqlDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mysqlDataSource);  // 设置数据源bean
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mappers/primary/*.xml"));  // 设置mapper文件路径

        return sessionFactory.getObject();
    }
    
}
