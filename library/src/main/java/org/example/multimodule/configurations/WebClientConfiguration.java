package org.example.multimodule.configurations;

import lombok.extern.log4j.Log4j2;
import org.example.multimodule.dto.ResponsePackageShow;
import org.example.multimodule.dto.ResponseResourceShow;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.models.ClientParams;
import org.example.multimodule.models.ProxyParams;
import org.example.multimodule.services.client.ODPClient;
import org.example.multimodule.services.client.impl.ODPClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Objects;

@Configuration
@Log4j2
public class WebClientConfiguration {

    @Bean(name = "packageShowOdpClient")
    public ODPClient<ResponsePackageShow> packageShowODPClient(@Autowired ClientParams clientParams,
                                                               @Autowired ProxyParams proxyParams) throws LoginException {
        log.traceEntry("ODPClient<ResponsePackageShow> initialization");

        ODPClientImpl<ResponsePackageShow> odpClient = new ODPClientImpl<>(clientParams, proxyParams);
        log.traceExit("ODPClient<ResponsePackageShow> initialized: \n" + odpClient);
        return odpClient;
    }

    @Bean(name = "resourceShowOdpClient")
    public ODPClient<ResponseResourceShow> resourceShowOdpClient(@Autowired ClientParams clientParams,
                                                                 @Autowired ProxyParams proxyParams) throws LoginException {
        log.traceEntry("ODPClient<ResponseResourceShow> initialization");
        ODPClientImpl<ResponseResourceShow> odpClient = new ODPClientImpl<>(clientParams, proxyParams);
        log.traceExit("ODPClient<ResponseResourceShow> initialized: \n" + odpClient);
        return odpClient;
    }

    @Bean
    public ProxyParams proxyParams(@Autowired ConfigurationStoredParameters parameters) {
        log.traceEntry("ProxyParams initialization");
        ProxyParams proxyParams = new ProxyParams(
                parameters.proxyHost(),
                parameters.proxyPort(),
                parameters.proxyAuth());
        log.traceExit("ProxyParams initialized: \n" + proxyParams);
        return proxyParams;
    }

    @Bean
    public ClientParams clientParams(@Autowired ConfigurationStoredParameters parameters) {
        log.traceEntry("ClientParams initialization started");
        ClientParams clientParams = new ClientParams(
                parameters.baseAddress(),
                parameters.u2cRemoteHttpsCredentials(),
                parameters.mediatype(),
                parameters.encoding(),
                parameters.connectionTimeout()
        );
        log.traceExit("ClientParams initialized: \n" + clientParams);
        return clientParams;
    }

    @Bean
    public Proxy proxy(@Autowired ProxyParams proxyParams) {

        if (Objects.isNull(proxyParams)
                || Objects.isNull(proxyParams.getHost())
                || Objects.isNull(proxyParams.getPort())
                || proxyParams.getPort() == 0) {
            log.info("Proxy is not set");
            return new Proxy(Proxy.Type.DIRECT,
                    new InetSocketAddress(
                            proxyParams.getHost(),
                            proxyParams.getPort()
                    ));
        }

        return log.traceExit("Proxy set ", new Proxy(
                        Proxy.Type.HTTP,
                        new InetSocketAddress(
                                proxyParams.getHost(),
                                proxyParams.getPort()
                        )
                )
        );
    }


}
