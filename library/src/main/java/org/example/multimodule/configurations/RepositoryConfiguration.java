package org.example.multimodule.configurations;

import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

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
}
