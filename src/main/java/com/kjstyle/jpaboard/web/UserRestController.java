package com.kjstyle.jpaboard.web;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.service.UserService;
import com.kjstyle.jpaboard.web.dto.UserSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserRestController
 */
@RestController
@RequiredArgsConstructor
public class UserRestController extends BaseRestController {

    private final UserService userService;

    /**
     * 사용자 등록
     *
     * @param userSaveReqDto
     * @return
     */
    @PostMapping("/users")
    public Long create(@RequestBody UserSaveReqDto userSaveReqDto) {
        return userService.save(userSaveReqDto);
    }

    /**
     * 사용자 리스트 (pageable)
     *
     * @param pageable
     * @return
     */
    @GetMapping("/users")
    public Page<User> list(@PageableDefault Pageable pageable) {
        return userService.findAll(pageable);
    }
}