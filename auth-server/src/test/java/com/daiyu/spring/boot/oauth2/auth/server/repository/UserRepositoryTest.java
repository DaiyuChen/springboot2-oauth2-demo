package com.daiyu.spring.boot.oauth2.auth.server.repository;

import com.daiyu.spring.boot.oauth2.auth.server.AuthServerApplication;
import com.daiyu.spring.boot.oauth2.auth.server.entity.AccessRight;
import com.daiyu.spring.boot.oauth2.auth.server.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daiyu Chen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testInsertUser() {
        UserEntity userEntity = userRepository.findByUsername("admin");
        if (userEntity == null) {
            List<String> accessRightList = new ArrayList<>();
            AccessRight[] accessRights = AccessRight.values();
            for (AccessRight accessRight : accessRights) {
                accessRightList.add(accessRight.name());
            }
            String rawAccessRights = String.join(",", accessRightList);
            userEntity = new UserEntity("admin", passwordEncoder.encode("123456"), rawAccessRights);
            userRepository.save(userEntity);
        }
    }

}