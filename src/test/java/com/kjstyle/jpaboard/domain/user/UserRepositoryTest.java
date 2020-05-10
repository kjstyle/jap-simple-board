package com.kjstyle.jpaboard.domain.user;

import com.kjstyle.jpaboard.exceptions.NoSuchUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    public static final String TEST_USER_ID = "kjstyle";

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 사용자_인서트_테스트() {
        // given
        User user = User.builder().userId(TEST_USER_ID).name("이길주").email("kjstyle79@naver.com").build();
        userRepository.save(user);

        // when
        User kj = userRepository.findUserByUserId(TEST_USER_ID).orElseThrow(() -> new NoSuchUserException());

        // then
        Assertions.assertEquals(kj.getUserId(), TEST_USER_ID);
        Assertions.assertNotNull(kj.getCreatedDate());
    }
}