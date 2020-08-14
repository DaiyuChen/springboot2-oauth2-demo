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
package com.daiyu.spring.boot.oauth2.auth.server.repository;

import com.daiyu.spring.boot.oauth2.auth.server.entity.OAuthClientDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Daiyu Chen
 */
@Repository
public interface OAuthClientDetailsRepository extends PagingAndSortingRepository<OAuthClientDetails, Long> {
    OAuthClientDetails findByClientId(String clientId);

    OAuthClientDetails findByGuid(String guid);
}
