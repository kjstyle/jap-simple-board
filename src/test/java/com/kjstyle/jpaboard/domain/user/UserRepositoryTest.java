package com.kjstyle.jpaboard.domain.user;

import com.kjstyle.jpaboard.common.BaseTest;
import com.kjstyle.jpaboard.exceptions.NoSuchUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends BaseTest {

    public static final String TEST_USER_ID = "kjstyle-test01";
    public static final String TEST_EMAIL = "kjstyle-test01@naver.com";

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 사용자_인서트_테스트() {
        // given
        User user = User.builder().userId(TEST_USER_ID).name("이길주").email(TEST_EMAIL).build();
        userRepository.save(user);

        // when
        User kj = userRepository.findUserByUserId(TEST_USER_ID).orElseThrow(() -> new NoSuchUserException());

        // then
        Assertions.assertEquals(kj.getUserId(), TEST_USER_ID);
        Assertions.assertEquals(kj.getEmail(), TEST_EMAIL);
        Assertions.assertNotNull(kj.getCreatedDate());
    }
}