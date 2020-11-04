package org.example.multimodule.infrastructure.impl;

import org.example.multimodule.infrastructure.Authenticator;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

public class BasicAuthenticator implements Authenticator {

    @Override
    public String getAuthenticationString(String userName, String password) {
       String token = userName + ":" + password;
       return "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes(StandardCharsets.UTF_8));
    }

}
