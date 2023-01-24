package com.example.holopportal;

import javax.inject.Inject;

import com.example.holopportal.user.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HolopPortalApplicationTests {

    @Inject
    UserService userService;

    @Test
    void contextLoads() {
        Assertions.assertNotEquals(userService, null);
    }

}
