package org.example.multimodule.annotations.constraints;

import lombok.extern.log4j.Log4j2;
import org.example.multimodule.exceptions.ODPClientException;
import org.example.multimodule.models.ProxyParams;
import org.example.multimodule.services.J2CInfo;
import org.example.multimodule.utils.Utils;

import javax.security.auth.login.LoginException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Log4j2
public class ProxyParamsValidator implements ConstraintValidator<ValidProxyParams, ProxyParams> {
    public void initialize(ValidProxyParams constraint) {
    }

    public boolean isValid(ProxyParams proxyParams, ConstraintValidatorContext context) {

        if (Objects.isNull(proxyParams)) {
            log.error("Proxy params validation error");
            throw new ODPClientException("Client params validation error");
        }
        String host = proxyParams.getHost();
        if (!Utils.isEmpty(host)) {
            hostVerification(host);
        }

        Integer port = proxyParams.getPort();
        if (!Utils.isEmpty(host) && (Objects.isNull(port) || port < 1 || port > 65535)) {
            log.error("Port is not set or incorrect, value = " + port);
            throw new ODPClientException("Port is not set or incorrect, value = " + port);
        }

        String proxyAuth = proxyParams.getProxyAuth();
        if (!Utils.isEmpty(host) && Utils.isEmpty(proxyAuth)) {
            log.warn("Proxy can deny anonymous access");
        }

        try {
            J2CInfo.getInstance(proxyAuth);
        } catch (LoginException e) {
            log.error("J2CInfo error " + proxyAuth, e);
            throw new ODPClientException("J2CInfo error " + proxyAuth, e);
        }

        return true;
    }

    private void hostVerification(String host) {
        if (!Utils.isEmpty(host)) {
            boolean isIP = Utils.isIp(host);
            if (isIP) {
                try {
                    InetAddress.getByAddress(host.getBytes(StandardCharsets.UTF_8));
                } catch (UnknownHostException e) {
                    log.error("Unknown host " + host);
                }
            } else {
                try {
                    InetAddress.getByName(host);
                } catch (UnknownHostException e) {
                    log.error("Unknown host " + host);
                }
            }
        }
    }

}
