package org.example.multimodule.service;

import com.ibm.wsspi.security.auth.callback.WSMappingCallbackHandler;
import lombok.Getter;
import lombok.Setter;
import org.example.multimodule.service.impl.J2CInfoImpl;

import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

public interface J2CInfo {

    static J2CInfoImpl getInstance(String j2cAlias) throws LoginException {
        J2CInfoImpl j2CInfo = new J2CInfoImpl();
        final Map<String, String> map = new HashMap<>();
        map.putIfAbsent("com.ibm.mapping.authDataAlias", j2cAlias);
        final WSMappingCallbackHandler callbackHandler = new WSMappingCallbackHandler(map, null);
        final LoginContext loginContext = new LoginContext("DefaultPrincipalMapping", callbackHandler);
        loginContext.login();
        j2CInfo.setPasswordCredential((PasswordCredential) loginContext.getSubject().getPrivateCredentials().toArray()[0]);
        loginContext.logout();
        return j2CInfo;
    }

    String getUser();
    String getPassword();
}
