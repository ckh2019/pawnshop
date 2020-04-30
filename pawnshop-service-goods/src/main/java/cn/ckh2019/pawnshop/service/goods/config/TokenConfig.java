package cn.ckh2019.pawnshop.service.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Chen Kaihong
 * 2020-02-18 14:42
 */
@Configuration
public class TokenConfig {

    private static final String SIGNING_KEY = "signing_key";

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenconverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenconverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;


    }
}
