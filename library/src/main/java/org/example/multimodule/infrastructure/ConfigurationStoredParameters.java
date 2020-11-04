package org.example.multimodule.infrastructure;

import org.aeonbits.owner.Config;

public interface ConfigurationStoredParameters extends Config {
    @Config.Key(value = "logLevel")
    String logLevel();

    @Config.Key(value = "logPath")
    String logPath();

    @Config.Key(value = "proxyHost")
    String proxyHost();

    @Config.Key(value = "proxyPort")
    Integer proxyPort();

    @Config.Key(value = "proxyAuth")
    String proxyAuth();

    @Config.Key(value = "baseAddress")
    String baseAddress();

    @Config.Key(value = "u2cRemoteHttpsCredentials")
    String u2cRemoteHttpsCredentials();

    @Config.Key(value = "mediatype")
    String mediatype();

    @Config.Key(value = "encoding")
    String encoding();

    @Config.Key(value = "connectionTimeout")
    Integer connectionTimeout();
}
