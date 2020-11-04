package org.example.multimodule.service;

import org.example.multimodule.annotations.constraints.ValidClientParams;
import org.example.multimodule.annotations.constraints.ValidProxyParams;
import org.example.multimodule.models.ClientParams;
import org.example.multimodule.models.ProxyParams;

import javax.security.auth.login.LoginException;

@FunctionalInterface
public interface ODPClient<T> {
    T getResponse(String url, String id, Class<T> tClass);
//    T getResponse(@ValidClientParams ClientParams clientParams, @ValidProxyParams ProxyParams proxyParams) throws LoginException;
}
