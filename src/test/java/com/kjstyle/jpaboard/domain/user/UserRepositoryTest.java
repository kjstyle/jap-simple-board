package com.kjstyle.jpaboard.domain.user;

import com.kjstyle.jpaboard.common.BaseJpaTest;
import com.kjstyle.jpaboard.exceptions.NoSuchUserException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
public class UserRepositoryTest extends BaseJpaTest {

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
        assertEquals(kj.getUserId(), TEST_USER_ID);
        assertEquals(kj.getEmail(), TEST_EMAIL);
        assertNotNull(kj.getCreatedDate());
    }
}