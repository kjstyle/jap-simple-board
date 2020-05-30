package com.kjstyle.jpaboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 회원관리 화면 Controller
 */
@Controller
public class UserController extends BaseController {

    @RequestMapping("/users")
    public String list() {
        return "user/list";
    }
}