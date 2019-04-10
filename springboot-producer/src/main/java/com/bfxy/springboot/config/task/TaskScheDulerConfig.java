package com.bfxy.springboot.config.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by linsong
 * 2018-10-13
 */
@Configuration
@EnableScheduling
public class TaskScheDulerConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskScheduler());
    }


    @Bean(destroyMethod="shutdown")
    public Executor taskScheduler(){
        return Executors.newScheduledThreadPool(100);
    }

}
