package com.bfxy.springboot.config.database;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * Created by linsong
 * 2018-10-13
 */

/*@Configuration
@AutoConfigureAfter(MybatisMapperScanerConfig.class)*/
public class MybatisMapperScanerConfig {
/*

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.bfxy.springboot.mapper");
        return mapperScannerConfigurer;
    }
*/

}
