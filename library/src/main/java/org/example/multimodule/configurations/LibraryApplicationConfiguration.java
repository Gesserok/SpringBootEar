package org.example.multimodule.configurations;

import lombok.extern.log4j.Log4j2;
import org.example.config.db.JDBCConfigurationFactory;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.infrastructure.LoggerConfigurator;
import org.example.multimodule.infrastructure.TimedCachebleScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class LibraryApplicationConfiguration {

    @Resource(lookup = "java:comp/env/instanceName")
    @Qualifier(value = "instanceName")
    private String instanceName;
    @Resource(lookup = "java:app/AppName")
    @Qualifier(value = "appName")
    private String appName;

    @Resource(lookup = "jdbc/configstorage")
    private DataSource configstorageDataSource;

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

}
