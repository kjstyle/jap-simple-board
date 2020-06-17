package com.kjstyle.jpaboard.web;

import com.kjstyle.jpaboard.common.BaseMockMvcTest;
import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRestMockControllerTest extends BaseMockMvcTest {

    @MockBean
    private UserService userService;

    private User mockUser;

    @BeforeEach
    void beforeEach() {
        mockUser = User.builder().userId("kj").email("kj@naver.com").id(1L).name("이길주").createdDate(LocalDateTime.now()).build();

    }

    @Test
    public void 회원조회테스트() throws Exception {
        // given

        given(userService.findById(1L)).willReturn(java.util.Optional.ofNullable(mockUser));


        // when
        final ResultActions actions = mockMvc.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("userId").value("kj"))
                .andExpect(jsonPath("name").value("이길주"))
                .andExpect(jsonPath("email").value("kj@naver.com"));
    }

    @Test
    public void 회원조회테스트_when으로해보기() throws Exception {

        // given
        when(userService.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockUser));


        // when
        final ResultActions actions = mockMvc.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("userId").value("kj"))
                .andExpect(jsonPath("name").value("이길주"))
                .andExpect(jsonPath("email").value("kj@naver.com"));


    }
}
