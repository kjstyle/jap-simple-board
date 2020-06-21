package com.kjstyle.jpaboard.service;

import com.kjstyle.jpaboard.common.BaseTest;
import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.web.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void 회원삭제처리테스트() throws Exception {

        // given
        userService.delete(1L);

        // when
        User user = userService.findById(1L);

        // then
        assertEquals(user.isDeleted(), true);
    }

    @Test
    public void 회원생성_테스트() {

        long id = userService.save(
                UserDto.Create.builder()
                        .name("이길주")
                        .userId("kjstye-service-insert")
                        .email("kjstyle-service-insert@naver.com")
                        .build()
        );

        User user = userService.findById(id);
        assertEquals("kjstye-service-insert", user.getUserId());
    }
}