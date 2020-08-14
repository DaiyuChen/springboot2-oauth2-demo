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
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

/**
 * @author Daiyu Chen
 */
@Entity
@Table(name = "oauth_client_details")
public class OAuthClientDetails extends BaseEntity implements ClientDetails {

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    /**
     * Separated by comma
     */
    @Column(name = "scope")
    private String scope;

    /**
     * Separated by comma
     */
    @Column(name = "resource_ids")
    private String resourceIds;

    /**
     * Separated by comma
     * authorization_code,client_credentials,password,refresh_token
     */
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     * Separated by comma
     */
    @Column(name = "redirect_uri")
    private String registeredRedirectUris;

    /**
     * Separated by comma
     */
    @Column(name = "autoapprove")
    private String autoApproveScopes;

    /**
     * Separated by comma
     */
    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private Integer accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValiditySeconds;

    /**
     * Map<String, Object>
     */
    @Column(name = "additional_information")
    private String additionalInformation;

    public OAuthClientDetails() {
    }

    public OAuthClientDetails(String clientId, String clientSecret, String scope, String resourceIds, String authorizedGrantTypes, String registeredRedirectUris, String autoApproveScopes, String authorities, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds, String additionalInformation) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.resourceIds = resourceIds;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris = registeredRedirectUris;
        this.autoApproveScopes = autoApproveScopes;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        if (StringUtils.hasText(resourceIds)) {
            return StringUtils.commaDelimitedListToSet(resourceIds);
        }
        return Collections.emptySet();
    }

    @Override
    public boolean isSecretRequired() {
        return clientSecret != null;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public boolean isScoped() {
        return StringUtils.hasText(scope);
    }

    @Override
    public Set<String> getScope() {
        return StringUtils.commaDelimitedListToSet(scope);
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (StringUtils.isEmpty(authorizedGrantTypes)) {
            return new HashSet<>(Arrays.asList("authorization_code", "client_credentials", "password", "refresh_token"));
        }
        return StringUtils.commaDelimitedListToSet(authorizedGrantTypes);
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return StringUtils.commaDelimitedListToSet(registeredRedirectUris);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (StringUtils.hasText(authorities)) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
        }
        return Collections.emptyList();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (StringUtils.isEmpty(autoApproveScopes)) {
            return false;
        }
        Set<String> autoApproveScopes = autoApproveScopes();
        for (String autoApproveScope : autoApproveScopes) {
            if (autoApproveScope.equals("true") || scope.matches(autoApproveScope)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Not implemented yet
     *
     * @return Empty
     */
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }

    public Set<String> autoApproveScopes() {
        return StringUtils.commaDelimitedListToSet(autoApproveScopes);
    }

}
