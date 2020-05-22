package com.kjstyle.jpaboard.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjstyle.jpaboard.web.dto.UserCreateReqDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc // Junit5에서는 이 어노테이션이 있어야 MockMvc주입에 문제가 안생김 (출처 : https://gofnrk.tistory.com/74)
@SpringBootTest
class UserRestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // 한글 깨짐 방지 처리
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

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
        mockMvc.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("userId").value("kj"))
                .andExpect(jsonPath("name").value("이길주"))
                .andExpect(jsonPath("email").value("kjstyle79@naver.com"))
                .andDo(print());
        ;
    }

    @Test
    public void 회원신규등록API테스트() throws Exception {
        String content = objectMapper.writeValueAsString(
                UserCreateReqDto.builder()
                        .userId("kjstyle2")
                        .name("이길주2")
                        .email("kjstyle99@naver.com")
                        .build()
        );

        mockMvc.perform(
                post("/api/users")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("3"))
                .andDo(print())
        ;
    }
}