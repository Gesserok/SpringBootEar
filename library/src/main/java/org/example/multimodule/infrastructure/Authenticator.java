package org.example.multimodule.infrastructure;


public interface Authenticator {

    String getAuthenticationString(String userName, String password);
}
