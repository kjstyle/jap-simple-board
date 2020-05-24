package com.kjstyle.jpaboard.web.dto;

import com.kjstyle.jpaboard.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원생성 DTO
 */
@Getter
@NoArgsConstructor
public class UserCreateReqDto {

    @ApiModelProperty(value = "회원 아이디", allowEmptyValue = false)
    private String userId;

    @ApiModelProperty(value = "회원 명", allowEmptyValue = false)
    private String name;

    @ApiModelProperty(value = "회원 이메일", allowEmptyValue = false)
    private String email;

    @Builder
    public UserCreateReqDto(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .name(this.name)
                .email(this.email)
                .build();
    }
}
