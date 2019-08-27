package com.cat.code.config.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: lvgang
 * @Time: 2019/8/15 15:52
 * @Email: lvgang@golaxy.cn
 * @Description: 第一数据库配置
 */
@Configuration
//前缀为spring.datasource.mysql的配置信息
@MapperScan(basePackages = "com.cat.code.mappers.secondary", sqlSessionFactoryRef = "secondaryMysqlSessionFactory")
public class SecondaryMysqlDataSourceConfig {

    @Bean(name = "secondaryMysqlDataSource", destroyMethod = "close",initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.secondary") // 将所有前缀为spring.datasource.mysql下的配置项都加载到DataSource中
    public DataSource secondaryMysqlDataSource() {
        return new DruidDataSource();
    }

    // 创建该数据源的事务管理
    @Bean(name = "secondaryMysqlTransactionManager")
    public DataSourceTransactionManager secondaryMysqlTransactionManager() {
        return new DataSourceTransactionManager(secondaryMysqlDataSource());
    }

    // 创建Mybatis的连接会话工厂实例

    @Bean(name = "secondaryMysqlSessionFactory")
    public SqlSessionFactory secondaryMysqlSessionFactory(@Qualifier("secondaryMysqlDataSource") DataSource mysqlDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mysqlDataSource);  // 设置数据源bean
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mappers/secondary/*.xml"));  // 设置mapper文件路径

        return sessionFactory.getObject();
    }
    
}
