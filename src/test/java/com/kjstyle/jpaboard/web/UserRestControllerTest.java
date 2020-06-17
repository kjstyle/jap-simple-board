package com.kjstyle.jpaboard.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjstyle.jpaboard.common.BaseMockMvcTest;
import com.kjstyle.jpaboard.web.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserRestControllerTest extends BaseMockMvcTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 회원조회() throws Exception {
        /*
        /api/users/1
        응답 예시
        {
            "createdDate": "2020-05-20T00:31:43.613696",
            "modifiedDate": "2020-05-20T00:31:43.613696",
            "id": 1,
            "userId": "kj",
            "name": "이길주",
            "email": "kjstyle79@naver.com"
        }
         */
        // when
        final ResultActions actions = mockMvc.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("userNo").value(1L))
                .andExpect(jsonPath("userId").value("kj"))
                .andExpect(jsonPath("name").value("이길주"))
                .andExpect(jsonPath("email").value("kjstyle79@naver.com"))
        ;
    }

    @Test
    public void 없는회원조회테스트() throws Exception {
        /*
        /api/users/0
        {
          "timestamp": "2020-05-29T15:14:59.942+0000",
          "status": 400,
          "error": "Bad Request",
          "message": "존재하지 않는 회원입니다.",
          "path": "/api/users/0"
        }

        // 아래는 로그에 찍힌 MockHttpServletResponse
        MockHttpServletResponse:
               Status = 400
        Error message = 존재하지 않는 회원입니다.
              Headers = []
         Content type = null
                 Body =
        Forwarded URL = null
       Redirected URL = null
              Cookies = []
         */
        mockMvc.perform(get("/api/users/0").contentType(MediaType.APPLICATION_JSON).content("json"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("존재하지 않는 회원입니다.")))
        ;
    }

    @Test
    public void 회원신규등록API테스트() throws Exception {
        // given
        String content = objectMapper.writeValueAsString(
                UserDto.Create.builder()
                        .userId("kjstyle2")
                        .name("이길주2")
                        .email("kjstyle99@naver.com")
                        .build()
        );

        // when
        final ResultActions actions = mockMvc.perform(
                post("/api/users")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(content().string("3"))
                .andDo(print())
        ;
    }

    @Test
    public void 회원신규등록API_유효성체크_짧은_ID_테스트() throws Exception {

        // given
        String content = objectMapper.writeValueAsString(
                UserDto.Create.builder()
                        .userId("kj")
                        .name("이길주")
                        .email("kj-id-test@naver.com")
                        .build()
        );

        // when
        final ResultActions actions = mockMvc.perform(
                post("/api/users")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isBadRequest())
                .andDo(print())
        ;
    }


    @Test
    public void 회원신규등록API_유효성체크_비정상이메일_테스트() throws Exception {
        String content = objectMapper.writeValueAsString(
                UserDto.Create.builder()
                        .userId("kjstyle-invalid-email")
                        .name("이길주")
                        .email("kj-invalid-email")
                        .build()
        );

        mockMvc.perform(
                post("/api/users")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void 회원정보변경API테스트() throws Exception {
        String content = objectMapper.writeValueAsString(
                UserDto.Update.builder()
                        .userNo(1L)
                        .userId("kjstyle-1")
                        .name("이길주-1")
                        .email("kjstyle-1@naver.com")
                        .build()
        );

        mockMvc.perform(
                put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId").value("kjstyle-1"))
                .andExpect(jsonPath("name").value("이길주-1"))
                .andExpect(jsonPath("email").value("kjstyle-1@naver.com"))
        ;

    }


    @Test
    public void 회원정보변경API_비정상이메일_테스트() throws Exception {
        String content = objectMapper.writeValueAsString(
                UserDto.Update.builder()
                        .userNo(1L)
                        .userId("kjstyle")
                        .name("이길주")
                        .email("kjstyle-invalid-email")
                        .build()
        );

        mockMvc.perform(
                put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void 회원이름으로회원조회테스트() throws Exception {
        mockMvc.perform(
                get("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .queryParam("search_type", "name")
                        .queryParam("search_keyword", "길주")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("이길주"))
        ;
    }

    @Test
    public void 회원아이디로회원조회테스트() throws Exception {
        mockMvc.perform(
                get("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .queryParam("search_type", "user_id")
                        .queryParam("search_keyword", "ejoin312")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("이어진"))
        ;
    }

    @Test
    public void 회원이메일로회원조회테스트() throws Exception {
        mockMvc.perform(
                get("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .queryParam("search_type", "email")
                        .queryParam("search_keyword", "eojin312")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("이어진"))
        ;
    }

}