package com.watercloud.webmagic.common.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class ShiroJWTToken implements AuthenticationToken {
    private String token;

    public ShiroJWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
