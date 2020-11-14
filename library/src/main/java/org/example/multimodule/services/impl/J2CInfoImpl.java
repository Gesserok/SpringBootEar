package org.example.multimodule.services.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.multimodule.services.J2CInfo;

import javax.resource.spi.security.PasswordCredential;

@Setter
@NoArgsConstructor(access = AccessLevel.NONE)
public class J2CInfoImpl implements J2CInfo {

    private PasswordCredential passwordCredential;
//
//    public J2CInfoImpl(String j2cAlias) throws LoginException {
//        final Map<String, String> map = new HashMap<>();
//        map.putIfAbsent("com.ibm.mapping.authDataAlias", j2cAlias);
//        final WSMappingCallbackHandler callbackHandler = new WSMappingCallbackHandler(map, null);
//        final LoginContext loginContext = new LoginContext("DefaultPrincipalMapping", callbackHandler);
//        loginContext.login();
//        final PasswordCredential result = (PasswordCredential) loginContext.getSubject().getPrivateCredentials().toArray()[0];
//        user = result.getUserName();
//        password = String.valueOf(result.getPassword());
//        loginContext.logout();
//    }
    @Override
    public String getUser() {
        return this.passwordCredential.getUserName();
    }
    @Override
    public String getPassword() {
        return String.valueOf(this.passwordCredential.getPassword());
    }

}
