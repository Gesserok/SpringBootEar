package org.example.multimodule.services.client.impl;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import lombok.extern.log4j.Log4j2;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.ProxyAuthorizationPolicy;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.auth.DefaultBasicAuthSupplier;
import org.apache.cxf.transport.http.auth.HttpAuthSupplier;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.example.multimodule.annotations.constraints.ValidClientParams;
import org.example.multimodule.annotations.constraints.ValidProxyParams;
import org.example.multimodule.exceptions.ODPClientException;
import org.example.multimodule.exceptions.ODPClientNotFoundException;
import org.example.multimodule.exceptions.ODPClientSocketTimeoutException;
import org.example.multimodule.infrastructure.impl.BasicAuthenticator;
import org.example.multimodule.models.ClientParams;
import org.example.multimodule.models.ProxyParams;
import org.example.multimodule.services.J2CInfo;
import org.example.multimodule.services.client.ODPClient;
import org.example.multimodule.services.impl.J2CInfoImpl;

import javax.security.auth.login.LoginException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
public class ODPClientImpl<T> implements ODPClient<T> {
    private final WebClient webClient;
    private final ClientParams clientParams;

    public ODPClientImpl(@ValidClientParams ClientParams clientParams, @ValidProxyParams ProxyParams proxyParams) throws LoginException {
        this.clientParams = clientParams;
        List<Object> providers = new ArrayList<>();
        providers.add(new JacksonJaxbJsonProvider());

        J2CInfo j2CInfo = J2CInfo.getInstance(clientParams.getU2cRemoteHttpsCredentials());

        this.webClient = WebClient.create(clientParams.getBaseAddress(), providers, true)
                .header("Authentication",
                        new BasicAuthenticator()
                                .getAuthenticationString(j2CInfo.getUser(), j2CInfo.getPassword()))
                .accept(clientParams.getMediaType())
                .type(clientParams.getMediaType())
                .encoding(clientParams.getEncoding());

        setHttpConduit(webClient, clientParams, proxyParams);
    }

    @Override
    public T getResponse(String url, String id, Class<T> tClass) {
        WebClient client = webClient.path(clientParams.getBaseAddress() + url);

        if (Objects.nonNull(id) && !id.isEmpty()) {
            client.query("id", id);
        }

        T response;
        try {
            response = client.get(tClass);
        } catch (ProcessingException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof SocketTimeoutException) {
                log.warn("Destination unreachable");
                throw new ODPClientSocketTimeoutException("Destination unreachable, url = " + client.getCurrentURI());
            }
            log.warn("ProcessingException");
            throw new ODPClientException("ProcessingException, url = " + client.getCurrentURI(), e);
        } catch (NotFoundException e) {
            log.warn("Method throw 404 Not Found Exception, url = " + client.getCurrentURI());
            throw new ODPClientNotFoundException("Method throw 404 Not Found Exception, url = " + client.getCurrentURI(), e);
        } finally {
            client.reset();
        }

        return response;
    }

    private void setHttpConduit(WebClient webClient, ClientParams clientParams, ProxyParams proxyParams) {
        HTTPConduit httpConduit = webClient.getConfiguration().getHttpConduit();
        httpConduit.setAuthSupplier(new DefaultBasicAuthSupplier());
        HTTPClientPolicy httpClientPolicy = httpConduit.getClient();
        httpClientPolicy.setConnectionRequestTimeout(clientParams.getConnectionTimeout());
        httpConduit.setClient(httpClientPolicy);
        TLSClientParameters tlsClientParameters = new TLSClientParameters();
        tlsClientParameters.setUseHttpsURLConnectionDefaultSslSocketFactory(true);
        tlsClientParameters.setUseHttpsURLConnectionDefaultHostnameVerifier(true);
        httpConduit.setTlsClientParameters(tlsClientParameters);
        if (Objects.nonNull(proxyParams.getHost())
                && Objects.nonNull(proxyParams.getProxyAuth())
                && proxyParams.getPort() != 0) {

            httpConduit.setProxyAuthorization(proxyAuthorizationPolicy(proxyParams));
        }
        httpConduit.setProxyAuthSupplier(basicAuthSupplier());
        if (Objects.nonNull(proxyParams.getHost())
                && Objects.nonNull(proxyParams.getProxyAuth())
                && proxyParams.getPort() != 0) {
            httpClientPolicy.setProxyServer(proxyParams.getHost());
            httpClientPolicy.setProxyServerPort(proxyParams.getPort());
        }
    }

    private HttpAuthSupplier basicAuthSupplier() {
        return new DefaultBasicAuthSupplier();
    }

    private ProxyAuthorizationPolicy proxyAuthorizationPolicy(ProxyParams proxyParams) {
        ProxyAuthorizationPolicy proxyAuthorizationPolicy = new ProxyAuthorizationPolicy();
        proxyAuthorizationPolicy.setAuthorizationType("Basic");
        String proxyAuth = proxyParams.getProxyAuth();
        J2CInfoImpl j2CInfo = null;
        try {
            j2CInfo = J2CInfo.getInstance(proxyAuth);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        assert j2CInfo != null;
        proxyAuthorizationPolicy.setUserName(j2CInfo.getUser());
        proxyAuthorizationPolicy.setPassword(j2CInfo.getPassword());
        return proxyAuthorizationPolicy;
    }

//
//    public ODPClientImpl(String... args) throws LoginException {
//        if (Objects.isNull(args) || args.length == 0) {
//            log.error("Args required");
//            return;
//        }
//        List<Object> providers = new ArrayList<>();
//        providers.add(new JacksonJaxbJsonProvider());
//
//        J2CInfo j2CInfo = J2CInfo.getInstance(args[1]);
//        this.webClient = WebClient.create(args[0], providers, true)
//                .header("Authentication",
//                        new BasicAuthenticator()
//                                .getAuthenticationString(j2CInfo.getUser(), j2CInfo.getPassword()))
//                .accept(args[2])
//                .type(args[3])
//                .encoding(args[4]);
//
//        setHttpConduit(webClient, args);
//    }
//

//
//    private void setHttpConduit(WebClient webClient, String[] args) {
//        HTTPConduit httpConduit = webClient.getConfiguration().getHttpConduit();
//        httpConduit.setAuthSupplier(new DefaultBasicAuthSupplier());
//        HTTPClientPolicy httpClientPolicy = httpConduit.getClient();
//        setProxyHost(httpClientPolicy, args);
//        httpClientPolicy.setConnectionRequestTimeout(Integer.parseInt(args[5]));
//        httpConduit.setClient(httpClientPolicy);
//        TLSClientParameters tlsClientParameters = new TLSClientParameters();
//        tlsClientParameters.setUseHttpsURLConnectionDefaultSslSocketFactory(true);
//        tlsClientParameters.setUseHttpsURLConnectionDefaultHostnameVerifier(true);
//        httpConduit.setTlsClientParameters(tlsClientParameters);
//        httpConduit.setProxyAuthorization(proxyAuthorizationPolicy(args));
//        httpConduit.setProxyAuthSupplier(basicAuthSupplier());
//        httpClientPolicy.setProxyServer(args[7]);
//        httpClientPolicy.setProxyServerPort(Integer.parseInt(args[8]));
//    }
//
//    private HttpAuthSupplier basicAuthSupplier() {
//    }
//
//    private ProxyAuthorizationPolicy proxyAuthorizationPolicy(String[] args) {
//    }
//
//    private void setProxyHost(HTTPClientPolicy httpClientPolicy, String...args) {
//
//        if (args.length < 9) {
//            log.debug("Proxy host or port is not set");
//            return;
//        }
//
//        if (Objects.isNull(args[7]) ||  )
//    }
}
