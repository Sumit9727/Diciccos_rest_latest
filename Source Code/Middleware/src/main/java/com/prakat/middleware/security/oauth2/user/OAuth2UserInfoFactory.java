package com.prakat.middleware.security.oauth2.user;


import java.util.Map;

import com.prakat.middleware.entity.AuthProvider;
import com.prakat.middleware.exception.BussinessException;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws BussinessException {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new BussinessException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
