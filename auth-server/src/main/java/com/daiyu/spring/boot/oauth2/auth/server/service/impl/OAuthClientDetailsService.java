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
package com.daiyu.spring.boot.oauth2.auth.server.service.impl;

import com.daiyu.spring.boot.oauth2.auth.server.entity.OAuthClientDetails;
import com.daiyu.spring.boot.oauth2.auth.server.repository.OAuthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;

/**
 * @author Daiyu Chen
 */
@Service
public class OAuthClientDetailsService implements ClientDetailsService {

    private final OAuthClientDetailsRepository clientDetailsRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OAuthClientDetailsService(OAuthClientDetailsRepository clientDetailsRepository, PasswordEncoder passwordEncoder) {
        this.clientDetailsRepository = clientDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsRepository.findByClientId(clientId);
    }

    public void createOrUpdateClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        updateClientDetails(clientDetails);
    }

    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        OAuthClientDetails oAuthClientDetails = (OAuthClientDetails) clientDetails;
        String clientSecret = clientDetails.getClientSecret();
        if (clientSecret != null) {
            clientSecret = passwordEncoder.encode(clientSecret);
        }
        oAuthClientDetails.setClientSecret(clientSecret);
        clientDetailsRepository.save(oAuthClientDetails);
    }

    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        OAuthClientDetails clientDetails = clientDetailsRepository.findByClientId(clientId);
        if (clientDetails == null) {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }
        clientDetails.setClientSecret(passwordEncoder.encode(secret));
        clientDetailsRepository.save(clientDetails);
    }

    public void removeClientDetails(String guid) throws NoSuchClientException {
        OAuthClientDetails clientDetails = clientDetailsRepository.findByGuid(guid);
        if (clientDetails != null) {
            clientDetailsRepository.delete(clientDetails);
        }
    }
}
