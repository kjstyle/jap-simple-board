package com.kjstyle.jpaboard.web.dto;

import com.kjstyle.jpaboard.domain.posts.Post;
import com.kjstyle.jpaboard.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
public class PostDto {

    @Getter
    public static class Create {

        @Positive
        @ApiModelProperty(value = "회원 번호", allowEmptyValue = false)
        private long userNo;

        @NotBlank(message = "제목을 입력해주세요")
        @ApiModelProperty(value = "글 제목", allowEmptyValue = false)
        private String title;

        @NotBlank(message = "내용을 입력해주세요")
        @ApiModelProperty(value = "글 내용", allowEmptyValue = false)
        @Size(min = 5, message = "5자 이상 입력해주세요")
        private String content;

        @Builder
        public Create(long userNo, String title, String content) {
            this.userNo = userNo;
            this.title = title;
            this.content = content;
        }

        public Post toEntity(User user) {
            return Post.builder()
                    .title(this.title)
                    .content(this.content)
                    .user(user)
                    .build();
        }
    }

}
