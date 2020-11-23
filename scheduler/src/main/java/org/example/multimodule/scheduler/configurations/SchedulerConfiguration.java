package org.example.multimodule.scheduler.configurations;

import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider.Configuration.builder;

@Configuration
@EnableScheduling
@Log4j2
public class SchedulerConfiguration {

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(4);
        return taskScheduler;
    }

    @Bean
    public LockProvider lockProvider(DataSource dataSource, PlatformTransactionManager transactionManager) {
        log.info("SHEDLOCK STARTED");
        JdbcTemplateLockProvider jdbcTemplateLockProvider = new JdbcTemplateLockProvider(builder()
                .withTableName("shedlock")
                .withColumnNames(new JdbcTemplateLockProvider.ColumnNames(
                        "name", "lock_until", "locked_at", "locked_by"))
                .withJdbcTemplate(new JdbcTemplate(dataSource, true))
                .withTransactionManager(transactionManager)
                .build());
        jdbcTemplateLockProvider.clearCache();
        log.info("SHEDLOCK FINISHED");
        return jdbcTemplateLockProvider;
    }
}
