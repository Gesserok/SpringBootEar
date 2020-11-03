package org.example.multimodule.configurations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "org.example.multimodule.dao")
public class RepositoryConfiguration {

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("ODPUnit");
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(@Autowired EntityManagerFactory entityManagerFactory) {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        return SessionFactoryUtils.getDataSource(sessionFactory);
    }

//    @Bean(name = "sessionFactory")
//    public SessionFactory sessionFactory(@Autowired EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory.unwrap(SessionFactory.class);
//    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Autowired DataSource dataSource,
            @Autowired EntityManagerFactory entityManagerFactory
    ) {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
