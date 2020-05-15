package com.kjstyle.jpaboard.domain.posts;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostRepositoryTest {

    public static final String TEST_TITLE = "코로나제발 쫌 끝나라";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 게시글_인서트_테스트() {

        // given and when
        User user = userRepository.save(User.builder()
                .userId("kjstyle")
                .name("이길주")
                .email("kjstyle79@naver.com")
                .build());

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