package com.kjstyle.jpaboard.web.dto;

import com.kjstyle.jpaboard.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원정보 변경용 DTO
 */
@Getter
@NoArgsConstructor
public class UserUpdateReqDto {
    @ApiModelProperty(value = "회원키", allowEmptyValue = false)
    private Long userNo;

    @ApiModelProperty(value = "회원 아이디", allowEmptyValue = false)
    private String userId;

    @ApiModelProperty(value = "회원 이름", allowEmptyValue = false)
    private String name;

    @ApiModelProperty(value = "회원 이메일", allowEmptyValue = false)
    private String email;

    @Builder
    public UserUpdateReqDto(Long userNo, String userId, String name, String email) {
        this.userNo = userNo;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
                .id(this.userNo)
                .userId(this.userId)
                .name(this.name)
                .email(this.email)
                .build();
    }
}
