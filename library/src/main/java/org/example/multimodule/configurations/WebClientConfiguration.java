package org.example.multimodule.configurations;

import org.example.multimodule.dto.ResponsePackageShow;
import org.example.multimodule.dto.ResponseResourceShow;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.models.ClientParams;
import org.example.multimodule.models.ProxyParams;
import org.example.multimodule.service.ODPClient;
import org.example.multimodule.service.impl.ODPClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.security.auth.login.LoginException;

public class WebClientConfiguration {

    @Bean(name = "packageShowOdpClient")
    public ODPClient<ResponsePackageShow> packageShowODPClient(@Autowired ClientParams clientParams,
                                                               @Autowired ProxyParams proxyParams) throws LoginException {
        return new ODPClientImpl<>(clientParams,proxyParams);
    }

    @Bean(name = "resourceShowOdpClient")
    public ODPClient<ResponseResourceShow> resourceShowOdpClient(@Autowired ClientParams clientParams,
                                                                 @Autowired ProxyParams proxyParams) throws LoginException {
        return new ODPClientImpl<>(clientParams,proxyParams);
    }

    @Bean
    public ProxyParams proxyParams(@Autowired ConfigurationStoredParameters parameters) {
        return new ProxyParams(
                parameters.proxyHost(),
                parameters.proxyPort(),
                parameters.proxyAuth());
    }

    @Bean
    private ClientParams clientParams(@Autowired ConfigurationStoredParameters parameters) {
        return new ClientParams(
                parameters.baseAddress(),
                parameters.u2cRemoteHttpsCredentials(),
                parameters.mediatype(),
                parameters.encoding(),
                parameters.connectionTimeout()
        );
    }


}
