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
package com.daiyu.spring.boot.oauth2.resource.server.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daiyu Chen
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/test")
//    @PreAuthorize("#oauth2.hasScope('TEST') and hasRole('ROLE_API')")
    @PreAuthorize("hasRole('ROLE_API')")
    @ResponseBody
    public String test() {
        return "Hello";
    }

}
