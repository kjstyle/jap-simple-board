package com.kjstyle.jpaboard.service;

import com.kjstyle.jpaboard.domain.posts.Post;
import com.kjstyle.jpaboard.domain.posts.PostRepository;
import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public Post create(PostDto.Create postDtoCreate) {
        User user = userService.findById(postDtoCreate.getUserNo());
        Post post = postRepository.save(postDtoCreate.toEntity(user));
        return post;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}