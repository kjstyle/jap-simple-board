package com.kjstyle.jpaboard.service;

import com.kjstyle.jpaboard.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void 회원삭제처리테스트() throws Exception {

        // given
        userService.delete(1L);

        // when
        User user = userService.findById(1L).orElseThrow(() -> new Exception());

        // then
        Assertions.assertEquals(user.isDeleted(), true);
    }
}