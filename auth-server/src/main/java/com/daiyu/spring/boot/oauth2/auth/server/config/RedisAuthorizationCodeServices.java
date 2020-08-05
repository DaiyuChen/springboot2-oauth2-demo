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

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

import java.nio.charset.StandardCharsets;

/**
 * Implemented by redis for storage authorization code
 * @author Daiyu Chen
 */
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    private static final String AUTHORIZATION_CODE_KEY_PREFIX = "AUTHORIZATION_CODE_KEY:";

    private final RedisConnectionFactory connectionFactory;

    public RedisAuthorizationCodeServices(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        byte[] key = generateKey(code);
        // The syntactic sugar can automatic resource management
        try (RedisConnection connection = connectionFactory.getConnection()) {
            connection.set(key, SerializationUtils.serialize(authentication));
        }
    }

    /**
     * Generate redis key by authorization code
     * @param code authorization code
     * @return redis key
     */
    protected byte[] generateKey(String code) {
        return (AUTHORIZATION_CODE_KEY_PREFIX + code).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        byte[] key = generateKey(code);
        // The syntactic sugar can automatic resource management
        try (RedisConnection connection = connectionFactory.getConnection()) {
            // retrieve OAuth2Authentication from redis by redis key
            byte[] authenticationBytes = connection.get(key);
            if (authenticationBytes == null) {
                return null;
            }
            OAuth2Authentication oAuth2Authentication = SerializationUtils.deserialize(authenticationBytes);
            // delete authorization code
            connection.del(key);
            return oAuth2Authentication;
        }
    }
}
