package com.bfxy.springboot.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by linsong
 * 2018-10-13
 */
/*@Configuration*/
public class MybatisDataSourceConfig {
/*

    @Autowired
    private DataSource dataSource;


    @Bean(value = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        //添加xml目录

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {


            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/bfxy/springboot/mapping/*.xml"));

            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
            sqlSessionFactory.getConfiguration().setCacheEnabled(Boolean.TRUE);

            return sqlSessionFactory;
        }catch (Exception e){
           throw new RuntimeException(e);
        }

    }*/
}
