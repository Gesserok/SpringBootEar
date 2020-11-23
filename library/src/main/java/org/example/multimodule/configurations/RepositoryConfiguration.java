package org.example.multimodule.configurations;

import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider.ColumnNames;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import static net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider.Configuration.builder;

@Configuration
@EnableJpaRepositories(basePackages = "org.example.multimodule.dao")
@Log4j2
@EnableTransactionManagement
public class RepositoryConfiguration {

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory() {
        log.fatal("-------------------------------------------------- 1 ----------------------------------------");
        return Persistence.createEntityManagerFactory("ODPUnit");
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(@Autowired EntityManagerFactory entityManagerFactory) {
        log.fatal("-------------------------------------------------- 2 ----------------------------------------");
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        return SessionFactoryUtils.getDataSource(sessionFactory);
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Autowired DataSource dataSource,
            @Autowired EntityManagerFactory entityManagerFactory
    ) {
        log.fatal("-------------------------------------------------- 3 ----------------------------------------");
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public LockProvider lockProvider(DataSource dataSource, PlatformTransactionManager transactionManager) {
        log.info("SHEDLOCK STARTED");
        JdbcTemplateLockProvider jdbcTemplateLockProvider = new JdbcTemplateLockProvider(builder()
                .withTableName("shedlock")
                .withColumnNames(new ColumnNames(
                        "name", "lock_until", "locked_at", "locked_by"))
                .withJdbcTemplate(new JdbcTemplate(dataSource, true))
                .withTransactionManager(transactionManager)
                .build());
        jdbcTemplateLockProvider.clearCache();
        log.info("SHEDLOCK FINISHED");
        return jdbcTemplateLockProvider;
    }
}
