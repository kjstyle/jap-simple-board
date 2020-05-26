package com.kjstyle.jpaboard.web;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.service.UserService;
import com.kjstyle.jpaboard.web.dto.UserCreateReqDto;
import com.kjstyle.jpaboard.web.dto.UserResDto;
import com.kjstyle.jpaboard.web.dto.UserUpdateReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * UserRestController
 */
@Api(value = "회원 API", tags = "user")
@RestController
@RequiredArgsConstructor
public class UserRestController extends BaseRestController {

    private final UserService userService;

    /**
     * 사용자 등록
     *
     * @param userCreateReqDto
     * @return
     */
    @ApiOperation("회원 등록")
    @PostMapping("/users")
    public Long create(@RequestBody @Valid UserCreateReqDto userCreateReqDto) {
        return userService.save(userCreateReqDto);
    }

    /**
     * 사용자 정보수정
     *
     * @param userUpdateReqDto
     * @return
     */
    @ApiOperation("회원 정보 변경")
    @PutMapping("/users")
    public UserResDto update(@RequestBody @Valid UserUpdateReqDto userUpdateReqDto) {
        User user = userService.save(userUpdateReqDto);
        return UserResDto.toDto(user);
    }

    /**
     * 사용자 단건 조회 byId
     *
     * @param id
     * @return
     */
    @ApiOperation("사용자 단건 조회 byId")
    @GetMapping("/users/{id}")
    public UserResDto getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다.") // ResponseStatusException since spring 5.0
        );
        return UserResDto.toDto(user);
    }

    /**
     * 사용자 삭제
     *
     * @param id
     * @return 삭제된 회원의 id(userNo)
     */
    @ApiOperation("회원 정보 변경")
    @DeleteMapping("/users/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }

    /**
     * 사용자 리스트 (pageable)
     *
     * @param pageable
     * @return
     */
    @ApiOperation("사용자 목록 조회 by pageable")
    @GetMapping("/users.pageable")
    public Page<User> list(@PageableDefault Pageable pageable) {
        return userService.findAllWithPage(pageable);
    }


    /**
     * 사용자 목록 조회 (이름 LIKE검색 가능)
     *
     * @param name
     * @param pageable
     * @return
     */
    @ApiOperation("사용자 목록 조회 by pageable")
    @GetMapping("/users")
    public Page<User> list(@RequestParam("name") String name, @PageableDefault Pageable pageable) {
        return userService.findAllNameLike(name, pageable);
    }
}