/*
 * Copyright (c) 2015 Igola Travel Consultant and Services Company Ltd.
 * www.igola.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Igola Travel Consultant and Services Company Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Igola Travel Consultant and Services Company Ltd.
 */

package com.daiyu.spring.boot.oauth2.auth.server.entity;

import com.daiyu.spring.boot.oauth2.auth.server.utils.GuidGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Derek Zheng
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Version
    @Column(name = "version", length = 11)
    private int version = 0;


    @Column(name = "guid")
    String guid = GuidGenerator.generate();


    @Column(name = "archived", length = 1)
    boolean archived = false;

    @Column(name = "created_datetime")
    protected LocalDateTime createdDateTime = LocalDateTime.now();


    @Column(name = "last_modified_datetime")
    LocalDateTime lastModifiedDateTime;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        lastModifiedDateTime = LocalDateTime.now();
    }

    public void updateLastModifiedDateTime(LocalDateTime localDateTime) {
        lastModifiedDateTime = localDateTime;
    }

    public void archive() {
        archived = true;
    }

    public void unarchive() {
        archived = false;
    }


    public boolean isArchived() {
        return archived;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BaseEntity)) {
            return false;
        }

        BaseEntity other = (BaseEntity) obj;

        // if the id is missing, return false
        if (guid() == null) {
            return false;
        }

        // equivalence by guid
        return guid().equals(other.guid());
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }

    public String guid() {
        return guid;
    }

    public int version() {
        return version;
    }

    public LocalDateTime createdDateTime() {
        return createdDateTime;
    }

    public LocalDateTime lastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public boolean isNew() {
        return id == null;
    }

    public long id() {
        return id;
    }

    public boolean isPersisted() {
        return id != null;
    }

    public void copyFrom(BaseEntity baseEntity) {
//        this.id = domainObject.id;
        this.guid = baseEntity.guid;
        this.archived = baseEntity.archived;
        this.version = baseEntity.version;
        this.createdDateTime = baseEntity.createdDateTime;
        this.lastModifiedDateTime = baseEntity.lastModifiedDateTime;
    }

    public void copyFromForUpdate(BaseEntity baseEntity) {
        this.id = baseEntity.id;
        this.guid = baseEntity.guid;
        this.archived = baseEntity.archived;
        this.version = baseEntity.version;
        this.createdDateTime = baseEntity.createdDateTime;
        this.lastModifiedDateTime = baseEntity.lastModifiedDateTime;
    }

    public void updateCreatedDateTime(LocalDateTime localDate) {
        this.createdDateTime = localDate;
    }

}