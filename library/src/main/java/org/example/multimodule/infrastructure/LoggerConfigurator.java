package org.example.multimodule.infrastructure;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;

@Component
@Log4j2
public class LoggerConfigurator {

    @Autowired
    private ConfigurationStoredParameters parameters;

    private static final String LOG_LEVEL_SYSTEM_PROPERTY = "app.logging.Level";
    private static final String LOG_PATH_SYSTEM_PROPERTY = "app.logging.Path";

    public void reconfigure() {
        if (isLoggerConfigured() && logPropertiesDidNotChange()) {
            return;
        }

        System.setProperty(LOG_LEVEL_SYSTEM_PROPERTY, parameters.logLevel());
        System.setProperty(LOG_PATH_SYSTEM_PROPERTY, parameters.logPath());

        final LoggerContext context = ((Logger) log).getContext();
        final URL configLocation = getClass().getClassLoader().getResource("log4j2.xml");

        try {
            context.setConfigLocation(Objects.requireNonNull(configLocation).toURI());
            log.info("Logger was reconfigured");
        } catch (Exception e) {
            log.catching(e);
        }
    }

    private boolean logPropertiesDidNotChange() {
        return StringUtils.equalsIgnoreCase(parameters.logLevel(), System.getProperty(LOG_LEVEL_SYSTEM_PROPERTY))
                && StringUtils.equalsIgnoreCase(parameters.logPath(), System.getProperty(LOG_PATH_SYSTEM_PROPERTY));
    }

    private boolean isLoggerConfigured() {
        return ((Logger)log).getContext().getConfigLocation() != null;
    }
}
