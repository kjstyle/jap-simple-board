package com.kjstyle.jpaboard.service;


import com.kjstyle.jpaboard.common.BaseTest;
import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class UserServiceMockTest extends BaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User mockUser;

    @BeforeEach
    void setMockObject() {
        this.mockUser = User.builder().userId("kj").email("kj@naver.com").id(1L).name("이길주").build();
        given(userRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(mockUser));
    }

    @Test
    public void 회원조회_테스트() {
        // given
        // beforeEach에서 수행

        // when
        User user = userService.findById(1L);

        // then
        Assertions.assertEquals(mockUser.getEmail(), user.getEmail());
    }
}
