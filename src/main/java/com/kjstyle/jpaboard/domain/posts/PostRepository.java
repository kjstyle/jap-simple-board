package com.kjstyle.jpaboard.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostByPostNo(Long postNo);
    Post findPostByTitleLike(String keyword);
    Post findPostByAuthor(String author);
}
