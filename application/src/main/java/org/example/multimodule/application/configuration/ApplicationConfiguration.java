package org.example.multimodule.application.configuration;

import lombok.extern.log4j.Log4j2;
import org.example.config.db.JDBCConfigurationFactory;
import org.example.multimodule.application.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.application.infrastructure.LoggerConfigurator;
import org.example.multimodule.application.infrastructure.TimedCachebleScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@Log4j2
public class ApplicationConfiguration {

    @Resource(lookup = "java:comp/env/instanceName")
    private String instanceName;
    @Resource(lookup = "java:app/AppName")
    private String appName;

    @Resource(lookup = "jdbc/configstorage")
    private DataSource configstorageDataSource;

    @Autowired
    public LoggerConfigurator loggerConfigurator;

    @Bean
    public ConfigurationStoredParameters configurationStoredParameters() {
        Object result = new TimedCachebleScope().get("storedParameters", () -> {

            try {
                return new JDBCConfigurationFactory(
                        configstorageDataSource.getConnection(),
                        appName,
                        instanceName)
                        .makeInstance(ConfigurationStoredParameters.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //noinspection ConstantConditions
            return null;
        });
        return (ConfigurationStoredParameters) result;
    }

    @EventListener(ContextStartedEvent.class)
    public void refresh() {
        loggerConfigurator.reconfigure();
        log.info("Context refreshed. Logger reconfigured.");
    }

}
