package org.example.multimodule.scheduler.configurations;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@Log4j2
public class SchedulerConfiguration {

    @Bean
    public LocalDateTime localDateTime() {
        log.info("-------------------LocalDateTime------------------------------");
        return LocalDateTime.now();
    }

//    @Bean
//    public TaskScheduler taskScheduler() {
//        final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.setPoolSize(4);
//        return taskScheduler;
//    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(4);
        return taskScheduler;
    }
}
