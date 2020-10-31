package org.example.multimodule.application.infrastructure;

import org.aeonbits.owner.Config;

public interface ConfigurationStoredParameters extends Config {
    @Config.Key(value = "logLevel")
    String logLevel();

    @Config.Key(value = "logPath")
    String logPath();

}
