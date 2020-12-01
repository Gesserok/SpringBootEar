package org.example.multimodule.scheduler.configurations;

import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.core.DefaultLockingTaskExecutor;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.util.concurrent.ForkJoinPool;

import static net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider.Configuration.builder;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "30s")
@Log4j2
public class SchedulerConfiguration {

    @Autowired
    private ConfigurationStoredParameters parameters;


    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(3);
        return taskScheduler;
    }

    @Bean
    public LockProvider lockProvider(DataSource dataSource, PlatformTransactionManager transactionManager) {
        log.info("SHEDLOCK STARTED");
        JdbcTemplateLockProvider jdbcTemplateLockProvider = new JdbcTemplateLockProvider(builder()
                .withTableName("odp.shedlock")
                .withColumnNames(new JdbcTemplateLockProvider.ColumnNames(
                        "name", "lock_until", "locked_at", "locked_by"))
                .withJdbcTemplate(new JdbcTemplate(dataSource, true))
                .withTransactionManager(transactionManager)
                .build());
        jdbcTemplateLockProvider.clearCache();
        log.info("SHEDLOCK FINISHED");
        return jdbcTemplateLockProvider;
    }

    @Bean
    public LockingTaskExecutor executor(LockProvider lockProvider) {
        return new DefaultLockingTaskExecutor(lockProvider);
    }

    @Bean
    public ForkJoinPool forkJoinPool() {

        ForkJoinPool forkJoinPool = new ForkJoinPool(
                parameters.threadPool(),
                ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                null,
                true);
        log.info("ForkJoinPool thread size = " + forkJoinPool.getPoolSize() + " " + forkJoinPool.getActiveThreadCount());
        return forkJoinPool;
    }
}
