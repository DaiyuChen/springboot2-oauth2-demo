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

import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Daiyu Chen
 */

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "access_rights")
    private String accessRights;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String accessRights) {
        this.username = username;
        this.password = password;
        this.accessRights = accessRights;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<AccessRight> accessRights() {
        if (StringUtils.hasText(accessRights)) {
            String[] accessRightArray = accessRights.split(",");
            Set<AccessRight> results = new HashSet<>();
            for (String accessRight : accessRightArray) {
                results.add(AccessRight.valueOf(accessRight));
            }
            return results;
        }
        return Collections.emptySet();
    }
}
