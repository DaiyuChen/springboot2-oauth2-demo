package com.daiyu.spring.boot.oauth2.auth.server.repository;

import com.daiyu.spring.boot.oauth2.auth.server.AuthServerApplication;
import com.daiyu.spring.boot.oauth2.auth.server.entity.OAuthClientDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author Daiyu Chen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApplication.class)
public class OAuthClientDetailsRepositoryTest {

    @Autowired
    private OAuthClientDetailsRepository clientDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInsertClient() {
        String clientId = "test";
        OAuthClientDetails oAuthClientDetails = clientDetailsRepository.findByClientId(clientId);
        if (oAuthClientDetails == null) {
            oAuthClientDetails = new OAuthClientDetails(clientId, passwordEncoder.encode("123456"), "USER,ORDER,TEST", null,
                    "authorization_code,client_credentials,password,refresh_token", "http://localhost:8000/auth/callback", null, "ROLE_TEST",
                    86400, 259200, null);
            clientDetailsRepository.save(oAuthClientDetails);
        }
    }

}