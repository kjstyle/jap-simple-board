package com.kjstyle.jpaboard.web.dto;

import com.kjstyle.jpaboard.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 회원생성 DTO
 */
@Getter
@NoArgsConstructor
public class UserCreateReqDto {

    @NotBlank(message = "회원 아이디를 입력해주세요.")
    @Size(min = 5, max = 30)
    @ApiModelProperty(value = "회원 아이디", allowEmptyValue = false)
    private String userId;

    @NotBlank(message = "회원 이름를 입력해주세요.")
    @ApiModelProperty(value = "회원 이름", allowEmptyValue = false)
    private String name;

    @NotBlank(message = "회원 이메일을 입력해주세요.")
    @Email(message = "이메일 양식을 지켜주세요")
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
