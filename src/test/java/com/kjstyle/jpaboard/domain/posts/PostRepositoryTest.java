package com.kjstyle.jpaboard.domain.posts;

import com.kjstyle.jpaboard.common.BaseTest;
import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
                .author(user.getUserId())
                .user(user)
                .build());

        // then
        Assertions.assertNotNull(post);
        Assertions.assertEquals(TEST_TITLE, post.getTitle());
    }

    @Test
    public void 게시글목록조회_테스트() throws Exception {

        // given
        User user = userRepository.findById(1L).orElseThrow(() -> new Exception());

        postRepository.save(Post.builder()
                .title("첫글")
                .content("한달만 더 참자")
                .author(user.getUserId())
                .user(user)
                .build());


        User user2 = userRepository.findById(2L).orElseThrow(() -> new Exception());

        postRepository.save(Post.builder()
                .title("두번째 글")
                .content("잘하자")
                .author(user2.getUserId())
                .user(user2)
                .build());
        // when
        List<Post> posts = postRepository.findAll();
        Post post = postRepository.findById(1L);
        Assertions.assertNotNull(post.getUser());

        // then
        Assertions.assertEquals("첫글", posts.get(0).getTitle());
    }
}