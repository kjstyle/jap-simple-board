package com.kjstyle.jpaboard.domain.user;

import com.kjstyle.jpaboard.common.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class UserRepositoryEntityManagerTest extends BaseTest {

    @Autowired
    private UserRepositoryEntityManager userRepositoryEntityManager;

    @Test
    public void 엔티티매니저로find() {
        User user = userRepositoryEntityManager.findById(1L);
        assertEquals("이길주", user.getName());
    }

    @Test
    public void insert해보기() {
        User user = User.builder()
                .name("홍길동")
                .userId("hong")
                .email("hong@naver.com")
                .build();

        long id = userRepositoryEntityManager.join(user);

        User newUser = userRepositoryEntityManager.findById(id);
        assertEquals("홍길동", newUser.getName());

    }
}