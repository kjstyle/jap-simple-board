package com.kjstyle.jpaboard.web;

import com.kjstyle.jpaboard.domain.posts.Post;
import com.kjstyle.jpaboard.service.PostService;
import com.kjstyle.jpaboard.web.dto.PostDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(value = "포스트 API", tags = "post")
@RestController
@RequiredArgsConstructor
public class PostRestController extends BaseRestController {
    private final PostService postService;

    @ApiOperation("Post 단건 조회")
    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable long id) {
        return postService.findById(id);
    }

    @ApiOperation("Post 리스트 조회")
    @GetMapping("/posts")
    public List<Post> list() {
        return postService.findAll();
    }

    @ApiOperation("Post 등록")
    @PostMapping("/posts")
    public Post create(@RequestBody @Valid PostDto.Create postDtoCreate) {
        return postService.create(postDtoCreate);
    }
}