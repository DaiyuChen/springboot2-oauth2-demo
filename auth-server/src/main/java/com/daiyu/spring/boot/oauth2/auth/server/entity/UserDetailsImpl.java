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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author Daiyu Chen
 */
public class UserDetailsImpl implements UserDetails {

    private String guid;

    private String username;

    private String password;

    private boolean archived;

    private Set<AccessRight> accessRights;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(UserEntity userEntity) {
        this.guid = userEntity.guid();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.archived = userEntity.archived;
        this.accessRights = userEntity.accessRights();
    }

    public String getGuid() {
        return guid;
    }

    public boolean isArchived() {
        return archived;
    }

    public Set<AccessRight> getAccessRights() {
        return accessRights;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return accessRights;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !archived;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !archived;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !archived;
    }

    @Override
    public boolean isEnabled() {
        return !archived;
    }
}
