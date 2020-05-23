package com.kjstyle.jpaboard.web.dto;

import com.kjstyle.jpaboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserDto {
    private Long userNo;
    private String userId;
    private String name;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public UserDto(Long userNo, String userId, String name, String email, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.userNo = userNo;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static UserDto toDto(User user) {
        return com.kjstyle.jpaboard.web.dto.UserDto.builder()
                .userNo(user.getId())
                .name(user.getName())
                .userId(user.getUserId())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }
}
