package com.kjstyle.jpaboard.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepositoryEntityManager {

    private final EntityManager em;

//    @PersistenceContext
//    private final EntityManager em;

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public Long join(User user) {
        em.persist(user);
        return user.getId();
    }
}