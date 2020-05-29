package com.kjstyle.jpaboard.web.dto;

import com.kjstyle.jpaboard.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class UserDto {

    @Getter
    public static class Create {
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
        public Create(String userId, String name, String email) {
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

    @Getter
    public static class Update {
        @NotNull
        @Min(1)
        @ApiModelProperty(value = "회원키", allowEmptyValue = false)
        private Long userNo;

        @NotBlank(message = "회원 아이디를 입력해주세요.")
        @Size(min = 5, max = 30, message = "아이디는 5자 이상 30자 이하만 가능합니다.")
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
        public Update(Long userNo, String userId, String name, String email) {
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
}
