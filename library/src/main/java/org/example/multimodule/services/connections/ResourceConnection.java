package org.example.multimodule.services.connections;

import org.example.multimodule.models.ResourceTask;

import java.net.URLConnection;

@FunctionalInterface
public interface ResourceConnection {
    URLConnection connection(ResourceTask resourceTask);
}
