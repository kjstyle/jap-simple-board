package com.kjstyle.jpaboard.web.dto;

import com.kjstyle.jpaboard.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 회원 응답 DTO
 */
@Getter
@NoArgsConstructor
public class UserResDto {
    @ApiModelProperty(value = "회원키")
    private Long userNo;

    @ApiModelProperty(value = "회원 아이디")
    private String userId;

    @ApiModelProperty(value = "회원 이름")
    private String name;

    @ApiModelProperty(value = "회원 이메일")
    private String email;

    @ApiModelProperty(value = "회원 등록일")
    private LocalDateTime createdDate;

    @ApiModelProperty(value = "회원정보 마지막수정일")
    private LocalDateTime modifiedDate;

    @Builder
    public UserResDto(Long userNo, String userId, String name, String email, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.userNo = userNo;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    /**
     * User타입을 UserResDto타입으로 변환
     *
     * @param user
     * @return
     */
    public static UserResDto toDto(User user) {
        return UserResDto.builder()
                .userNo(user.getId())
                .name(user.getName())
                .userId(user.getUserId())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }
}
