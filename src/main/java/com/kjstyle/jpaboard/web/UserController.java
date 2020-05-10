package com.kjstyle.jpaboard.web;

import com.kjstyle.jpaboard.service.UserService;
import com.kjstyle.jpaboard.web.dto.UserSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public Long save(@RequestBody UserSaveReqDto userSaveReqDto) {
        return userService.save(userSaveReqDto);
    }
}