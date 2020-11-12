package org.example.multimodule.application.configuration;

import lombok.extern.log4j.Log4j2;
import org.example.multimodule.infrastructure.LoggerConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.*;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@Log4j2
public class WebAppConfiguration {
    //
//    @Resource(lookup = "java:comp/env/instanceName")
//    private String instanceName;
//    @Resource(lookup = "java:app/AppName")
//    private String appName;
//
//    @Resource(lookup = "jdbc/configstorage")
//    private DataSource configstorageDataSource;
//
    @Autowired
    public LoggerConfigurator loggerConfigurator;
//
//    @Bean
//    public ConfigurationStoredParameters configurationStoredParameters() {
//        Object result = new TimedCachebleScope().get("storedParameters", () -> {
//
//            try {
//                return new JDBCConfigurationFactory(
//                        configstorageDataSource.getConnection(),
//                        appName,
//                        instanceName)
//                        .makeInstance(ConfigurationStoredParameters.class);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            //noinspection ConstantConditions
//            return null;
//        });
//        return (ConfigurationStoredParameters) result;
//    }

    @EventListener(ApplicationContextInitializedEvent.class)
    public void webServerInitializedEvent() {
        loggerConfigurator.reconfigure();
        log.info("WebServerInitializedEvent. Logger reconfigured.");
    }

    @EventListener(ApplicationEvent.class)
    public void appContext1() {
        loggerConfigurator.reconfigure();
        log.info("ApplicationEvent. Logger reconfigured.");
    }

    @EventListener(ApplicationPreparedEvent.class)
    public void appPrep() {
        loggerConfigurator.reconfigure();
        log.info("ApplicationPreparedEvent. Logger reconfigured.");
    }

    @EventListener(ApplicationStartedEvent.class)
    public void appStarted() {
        loggerConfigurator.reconfigure();
        log.info("ApplicationStartedEvent. Logger reconfigured.");
    }

    @EventListener(ApplicationStartingEvent.class)
    public void appStarting() {
        loggerConfigurator.reconfigure();
        log.info("ApplicationStartingEvent. Logger reconfigured.");
    }

    @EventListener(ApplicationContextEvent.class)
    public void appContext() {
        loggerConfigurator.reconfigure();
        log.info("ApplicationContextEvent. Logger reconfigured.");
    }

    @EventListener(ContextStartedEvent.class)
    public void start() {
        loggerConfigurator.reconfigure();
        log.info("ContextRefreshedEvent. Logger reconfigured.");
    }

    @EventListener(ContextRefreshedEvent.class)
    public void refresh() {
        loggerConfigurator.reconfigure();
        log.info("ContextRefreshedEvent. Logger reconfigured.");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup1() {
        loggerConfigurator.reconfigure();
        log.info("ApplicationReadyEvent. Logger reconfigured.");
    }

}
