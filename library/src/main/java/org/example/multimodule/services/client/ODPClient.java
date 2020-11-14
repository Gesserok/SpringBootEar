package org.example.multimodule.services.client;

//@FunctionalInterface
public interface ODPClient<T> {
    T getResponse(String url, String id, Class<T> tClass);
}
