package com.kjstyle.jpaboard.service;

import com.kjstyle.jpaboard.common.BaseTest;
import com.kjstyle.jpaboard.domain.posts.Post;
import com.kjstyle.jpaboard.web.dto.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PostServiceTest extends BaseTest {

    @Autowired
    private PostService postService;

    @Test
    public void 글저장테스트() {

        // given
        PostDto.Create dto = PostDto.Create.builder()
                .title("첫글")
                .content("잘부탁드립니다.5자 이상 맞죠??")
                .userNo(1L)
                .build();

        // when
        Post newPost = postService.create(dto);

        // then
        Assertions.assertTrue(newPost.getId() > 0);
    }

    @Test
    public void 글리스트_가져오기() {

        // given
        PostDto.Create dto = PostDto.Create.builder()
                .title("첫글")
                .content("잘부탁드립니다.5자 이상 맞죠??")
                .userNo(1L)
                .build();
        postService.create(dto);

        PostDto.Create dto2 = PostDto.Create.builder()
                .title("두번째 글")
                .content("5자 채우기 위한 노오력")
                .userNo(2L)
                .build();
        postService.create(dto2);

        // when
        List<Post> posts = postService.findAll();

        // then
        Assertions.assertNotNull(posts.get(0).getUser());
    }
}
