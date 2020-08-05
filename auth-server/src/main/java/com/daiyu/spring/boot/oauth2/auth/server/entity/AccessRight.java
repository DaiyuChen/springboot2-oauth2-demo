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
package com.daiyu.spring.boot.oauth2.auth.server.entity;

import com.daiyu.spring.boot.oauth2.auth.server.SecurityConstants;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Daiyu Chen
 */
public enum  AccessRight implements GrantedAuthority {

    ADMIN,
    USER,
    API;

    @Override
    public String getAuthority() {
        return SecurityConstants.ROLE + name();
    }
}
