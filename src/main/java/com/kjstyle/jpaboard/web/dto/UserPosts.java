package com.kjstyle.jpaboard.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserPosts {
    List<PostDto> posts;
    private long userNo;

    @Getter
    public static class PostDto {
        private long postNo;
    }
}