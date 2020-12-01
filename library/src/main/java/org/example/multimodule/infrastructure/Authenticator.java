package org.example.multimodule.infrastructure;

@FunctionalInterface
public interface Authenticator {
    String getAuthenticationString(String userName, String password);
}
