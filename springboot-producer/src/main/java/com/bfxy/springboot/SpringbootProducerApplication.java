package com.bfxy.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
/*@MapperScan("com.bfxy.springboot.mapper")*/
//@EnableScheduling
public class SpringbootProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProducerApplication.class, args);
    }

    /*@Bean
    public Queue CreateRabbit(){
        return new Queue("OrderQueue2");
    }*/
}
