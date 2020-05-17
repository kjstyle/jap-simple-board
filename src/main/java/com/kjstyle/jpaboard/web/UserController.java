package com.kjstyle.jpaboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class UserController extends BaseController {

    @RequestMapping("/users")
    public String list() {
        return "user/list";
    }
}
