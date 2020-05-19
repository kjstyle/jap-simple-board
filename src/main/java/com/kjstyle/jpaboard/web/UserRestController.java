package com.kjstyle.jpaboard.web;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.service.PaginationParam;
import com.kjstyle.jpaboard.service.UserService;
import com.kjstyle.jpaboard.web.dto.ListResponse;
import com.kjstyle.jpaboard.web.dto.UserSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

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
    @GetMapping("/users/pageable")
    public Page<User> list(@PageableDefault Pageable pageable) {
        return userService.findAllWithPage(pageable);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }

    /**
     * 사용자 리스트 (bootgrid용)
     * http://www.jquery-bootgrid.com/Examples#basic
     *
     * @param paginationParam
     * @return
     */
    @PostMapping("/users/bootgrid")
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