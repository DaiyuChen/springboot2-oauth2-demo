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
package com.daiyu.spring.boot.oauth2.auth.server.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * @author Daiyu Chen
 */
public class GuidGenerator {

    private static final TimeBasedGenerator TIME_BASED_GENERATOR = Generators.timeBasedGenerator(EthernetAddress.constructMulticastAddress());

    public static String generate() {
        return TIME_BASED_GENERATOR.generate().toString();
    }
}
