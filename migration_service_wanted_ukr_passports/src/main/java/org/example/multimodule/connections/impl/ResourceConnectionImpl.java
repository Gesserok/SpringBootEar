package org.example.multimodule.connections.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.connections.ResourceConnection;
import org.example.multimodule.exceptions.ODPMalformedURLException;
import org.example.multimodule.exceptions.ODPURLConnectionException;
import org.example.multimodule.models.ResourceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

@Component
@AllArgsConstructor (onConstructor = @__({@Autowired}))
@Log4j2
public class ResourceConnectionImpl implements ResourceConnection {

    private final Proxy proxy;

    @Override
    public URLConnection connection(ResourceTask resourceTask) {
        URL url;

        try {
            url = new URL(resourceTask.getUrl());
        } catch (MalformedURLException e) {
            log.error("URL is incorrect");
            throw new ODPMalformedURLException("URL is incorrect", e);
        }

        try {
            return Objects.isNull(proxy) ? url.openConnection() : url.openConnection(proxy);
        } catch (IOException e) {
            log.error("Connection is not established");
            throw new ODPURLConnectionException("Connection is not established", e);
        }
    }
}
