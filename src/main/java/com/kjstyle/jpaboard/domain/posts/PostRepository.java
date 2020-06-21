package com.kjstyle.jpaboard.domain.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p join p.user u", Post.class)
                .setMaxResults(100)
                .getResultList();
    }

    public Post findById(long id) {
        return em.find(Post.class, id);
    }
}