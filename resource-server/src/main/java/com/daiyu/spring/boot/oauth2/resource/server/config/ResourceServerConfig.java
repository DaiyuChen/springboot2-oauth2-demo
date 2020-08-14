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
package com.daiyu.spring.boot.oauth2.resource.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author Daiyu Chen
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private String checkTokenUri;

    private String clientId;

    private String clientSecret;

    @Value("${security.oauth2.resource.check-token-uri}")
    public void setCheckTokenUri(String checkTokenUri) {
        this.checkTokenUri = checkTokenUri;
    }

    @Value("${security.oauth2.client.client-id}")
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Value("${security.oauth2.client.client-secret}")
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Bean
    @Primary
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId(clientId);
        tokenServices.setClientSecret(clientSecret);
        tokenServices.setCheckTokenEndpointUrl(checkTokenUri);
        return tokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.requestMatchers()
//                .antMatchers("/api/**")
//                .and().authorizeRequests().antMatchers("/api/user/profile").authenticated()
////                .antMatchers(HttpMethod.DELETE, "/oauth/revoke_token").authenticated()
//                .antMatchers("/api/**").access("#oauth2.hasScope('AUTH')");

        http.requestMatchers().antMatchers("/api/**")
                .and().authorizeRequests().antMatchers("/api/**").access("#oauth2.hasScope('TEST') and hasRole('ROLE_API')");
    }
}
