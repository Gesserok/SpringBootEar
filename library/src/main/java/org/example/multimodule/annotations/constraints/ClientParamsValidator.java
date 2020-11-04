package org.example.multimodule.annotations.constraints;

import lombok.extern.log4j.Log4j2;
import org.example.multimodule.exceptions.ODPClientException;
import org.example.multimodule.models.ClientParams;
import org.example.multimodule.service.J2CInfo;
import org.example.multimodule.utils.Utils;
import org.springframework.http.MediaType;

import javax.security.auth.login.LoginException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.Charset;
import java.util.Objects;

@Log4j2
public class ClientParamsValidator implements ConstraintValidator<ValidClientParams, ClientParams> {

    public void initialize(ValidClientParams constraint) {

    }

    public boolean isValid(ClientParams clientParams, ConstraintValidatorContext context) {

        if (Objects.isNull(clientParams)) {
            log.error("Client params validation error");
            throw new ODPClientException("Client params validation error");
        }

        String baseAddress = clientParams.getBaseAddress();
        if (Utils.isEmpty(baseAddress) || !baseAddress.startsWith("https://")) {
            log.error("Client base address or protocol is failed: " + baseAddress);
            throw new ODPClientException("Client base address or protocol is failed: " + baseAddress);
        }

        String u2cRemoteHttpsCredentials = clientParams.getU2cRemoteHttpsCredentials();
        if (Utils.isEmpty(u2cRemoteHttpsCredentials)) {
            log.error("Client u2cRemoteHttpsCredentials is failed: " + u2cRemoteHttpsCredentials);
            throw new ODPClientException("Client u2cRemoteHttpsCredentials is failed: " + u2cRemoteHttpsCredentials);
        }

        try {
            J2CInfo.getInstance(u2cRemoteHttpsCredentials);
        } catch (LoginException e) {
            log.error("LoginException client validator");
            throw new ODPClientException("LoginException client validator");
        }

        String encoding = clientParams.getEncoding();
        if (Utils.isEmpty(encoding) || !Charset.availableCharsets().containsKey(encoding)) {
            log.error("Client encoding is failed: " + encoding);
            throw new ODPClientException("Client encoding is failed: " + encoding);
        }

        String mediaType = clientParams.getMediaType();
        if (Utils.isEmpty(mediaType)) {
            log.error("Client mediaType is failed: " + mediaType);
            throw new ODPClientException("Client mediaType is failed: " + mediaType);
        }

        Integer connectionTimeout = clientParams.getConnectionTimeout();
        if (Objects.isNull(connectionTimeout) || connectionTimeout < 0) {
            log.error("Client mediaType is failed: " + connectionTimeout);
            throw new ODPClientException("Client mediaType is failed: " + connectionTimeout);
        }

        return true;
    }
}
