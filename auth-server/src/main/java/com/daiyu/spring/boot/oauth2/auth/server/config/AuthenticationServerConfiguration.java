/*
 * Copyright (c) 2016 Tianbao Travel Ltd.
 * www.tianbaotravel.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Tianbao Travel Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Tianbao Travel Ltd.
 */
package com.daiyu.spring.boot.oauth2.auth.server.config;

import com.daiyu.spring.boot.oauth2.auth.server.CacheConstants;
import com.daiyu.spring.boot.oauth2.auth.server.SecurityConstants;
import com.daiyu.spring.boot.oauth2.auth.server.entity.UserDetailsImpl;
import com.daiyu.spring.boot.oauth2.auth.server.service.impl.OAuthClientDetailsService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daiyu Chen
 */
@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final HikariDataSource dataSource;

    private final RedisConnectionFactory redisConnectionFactory;

    private final OAuthClientDetailsService oAuthClientDetailsService;

    @Autowired
    public AuthenticationServerConfiguration(HikariDataSource dataSource, RedisConnectionFactory redisConnectionFactory, OAuthClientDetailsService oAuthClientDetailsService) {
        this.dataSource = dataSource;
        this.redisConnectionFactory = redisConnectionFactory;
        this.oAuthClientDetailsService = oAuthClientDetailsService;
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new RedisAuthorizationCodeServices(redisConnectionFactory);
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(4);
            UserDetailsImpl user = (UserDetailsImpl) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put(SecurityConstants.DETAILS_USER_ID, user.getGuid());
            additionalInfo.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
        return tokenStore;
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // oauth_client_details
        clients.withClientDetails(oAuthClientDetailsService);
//        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }


    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // oauth_approvals
        endpoints.approvalStore(approvalStore());
        // oauth_code
        endpoints.authorizationCodeServices(authorizationCodeServices());
        // oauth_access_token & oauth_refresh_token
        endpoints.tokenStore(tokenStore());
        endpoints.tokenEnhancer(tokenEnhancer());
        // 支持password grant type
        endpoints.authenticationManager(authenticationManager);


    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients();
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
    }
}
