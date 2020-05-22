package com.kjstyle.jpaboard.web;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.service.PaginationParam;
import com.kjstyle.jpaboard.service.UserService;
import com.kjstyle.jpaboard.web.dto.ListResponse;
import com.kjstyle.jpaboard.web.dto.UserCreateReqDto;
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

import java.util.List;

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
    public Long create(@RequestBody UserCreateReqDto userCreateReqDto) {
        return userService.save(userCreateReqDto);
    }

    /**
     * 사용자 정보수정
     *
     * @param userUpdateReqDto
     * @return
     */
    @ApiOperation("회원 등록")
    @PutMapping("/users")
    public User update(@RequestBody UserUpdateReqDto userUpdateReqDto) {
        return userService.save(userUpdateReqDto);
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

    @ApiOperation("사용자 단건 조회 byId")
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다.") // ResponseStatusException since spring 5.0
        );
    }

    /**
     * 사용자 리스트 (bootgrid용)
     * http://www.jquery-bootgrid.com/Examples#basic
     *
     * @return
     */
    @PostMapping("/users.bootgrid")
    public ListResponse<User> list4BootGrid() {
        PaginationParam paginationParam = new PaginationParam(10, 10, "asc");
        List<User> userList = userService.findAll(paginationParam);
        return ListResponse.<User>builder()
                .rows(userList)
                .current(paginationParam.getCurrent())
                .rowCount(paginationParam.getRowCount())
                .totalCount(1000)
                .build();
    }
}