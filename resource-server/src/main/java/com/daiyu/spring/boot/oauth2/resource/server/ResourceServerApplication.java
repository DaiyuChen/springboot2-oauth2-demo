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
package com.daiyu.spring.boot.oauth2.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Daiyu Chen
 */
@SpringBootApplication
public class ResourceServerApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ResourceServerApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
