package com.SECURITY.securityTake2.JWT;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

//  Služi nam da bi osigurali secret key
@ConfigurationProperties(prefix = "application.jwt")
@Configuration
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationDayes;

    public JwtConfig() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationDayes() {
        return tokenExpirationDayes;
    }

    public void setTokenExpirationDayes(Integer tokenExpirationDayes) {
        this.tokenExpirationDayes = tokenExpirationDayes;
    }

    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }
}
