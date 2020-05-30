package com.kjstyle.jpaboard.domain.posts;

import com.kjstyle.jpaboard.common.BaseTest;
import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PostRepositoryTest extends BaseTest {

    public static final String TEST_TITLE = "코로나제발 쫌 끝나라";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 게시글_인서트_테스트() throws Exception {

        // given and when
        User user = userRepository.findById(1L).orElseThrow(() -> new Exception());

        Post post = postRepository.save(Post.builder()
                .title(TEST_TITLE)
                .content("한달만 더 참자")
                .author("코로나시러")
                .user(user)
                .build());

        // then
        Assertions.assertNotNull(post);
        Assertions.assertEquals(TEST_TITLE, post.getTitle());
    }
}