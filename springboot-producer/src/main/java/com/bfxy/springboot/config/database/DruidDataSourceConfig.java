package com.bfxy.springboot.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by linsong
 * 2018-10-13
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);

    @Autowired
    private DruidDataSourceSettings druidDataSourceSettings;

    public static String DRIVER_CLASSNAME;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() throws SQLException{
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(druidDataSourceSettings.getDriverClassName());
        DRIVER_CLASSNAME = druidDataSourceSettings.getDriverClassName();
        ds.setUrl(druidDataSourceSettings.getUrl());
        ds.setUsername(druidDataSourceSettings.getUsername());
        ds.setPassword(druidDataSourceSettings.getPassword());
        ds.setInitialSize(druidDataSourceSettings.getInitialSize());
        ds.setMinIdle(druidDataSourceSettings.getMinIdle());
        ds.setMaxActive(druidDataSourceSettings.getMaxActive());
        ds.setTimeBetweenEvictionRunsMillis(druidDataSourceSettings.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(druidDataSourceSettings.getMinEvictableIdleTimeMillis());
        ds.setValidationQuery(druidDataSourceSettings.getValidationQuery());
        //ds.setTestWhileIdle(druidDataSourceSettings.isTestWhileIdle());
        ds.setTestOnBorrow(druidDataSourceSettings.isTestOnBorrow());
        ds.setTestOnReturn(druidDataSourceSettings.isTestOnReturn());
        ds.setPoolPreparedStatements(druidDataSourceSettings.isPoolPreparedStatements());
        ds.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceSettings.getMaxPoolPreparedStantementPerConnectionSize());

        logger.info("druid datasource config : {}",ds);

        return ds;
    }

    //设置事务
    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());

        return transactionManager;
    }
}
