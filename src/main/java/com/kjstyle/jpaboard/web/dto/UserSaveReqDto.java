package com.kjstyle.jpaboard.web.dto;

import com.kjstyle.jpaboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveReqDto {

    private String userId;
    private String name;
    private String email;

    @Builder
    public UserSaveReqDto(String userId, String name, String email) {
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
