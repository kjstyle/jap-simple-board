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

    @Mock // 테스트할 대상 클래스 내에서 사용되는 의존성에 대해 가짜로 대치시킴
    private UserRepository userRepository;

    @InjectMocks // 테스트할 대상 클래스
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
