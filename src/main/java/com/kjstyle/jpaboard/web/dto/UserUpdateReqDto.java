package com.kjstyle.jpaboard.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateReqDto {
    private String userId;
    private String name;
    private String email;
}
