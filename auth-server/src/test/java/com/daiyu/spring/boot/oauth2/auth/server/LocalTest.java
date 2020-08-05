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
package com.daiyu.spring.boot.oauth2.auth.server;

import org.junit.Test;

/**
 * @author Daiyu Chen
 */
public class LocalTest {


    @Test
    public void testGenerateLoginPage() {
        System.out.println(generateLoginPageHtml());

    }

    public String generateLoginPageHtml() {

        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "  <head>\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
                + "    <meta name=\"description\" content=\"\">\n"
                + "    <meta name=\"author\" content=\"\">\n"
                + "    <title>Please sign in</title>\n"
                + "    <link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">\n"
                + "    <link href=\"https://getbootstrap.com/docs/4.0/examples/signin/signin.css\" rel=\"stylesheet\" crossorigin=\"anonymous\"/>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "     <div class=\"container\">\n");

        sb.append("      <form class=\"form-signin\" method=\"post\" action=\"/login\">\n"
                + "        <h2 class=\"form-signin-heading\">Please sign in</h2>\n"
                + "        <div class=\"alert alert-danger\" role=\"alert\">登陆失败</div>"
                + "        <div class=\"alert alert-success\" role=\"alert\">You have been signed out</div>"
                + "        <p>\n"
                + "          <label for=\"username\" class=\"sr-only\">Username</label>\n"
                + "          <input type=\"text\" id=\"username\" name=\"username\" class=\"form-control\" placeholder=\"Username\" required autofocus>\n"
                + "        </p>\n"
                + "        <p>\n"
                + "          <label for=\"password\" class=\"sr-only\">Password</label>\n"
                + "          <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Password\" required>\n"
                + "        </p>\n"
                + "        <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign in</button>\n"
                + "      </form>\n");
        sb.append("</div>\n");
        sb.append("</body></html>");

        return sb.toString();
    }

}
