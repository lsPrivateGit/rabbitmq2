package com.bfxy.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.bfxy.springboot.mapper")
public class SpringbootProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProducerApplication.class, args);
    }

}
