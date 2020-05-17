package com.kjstyle.jpaboard.web;

import com.kjstyle.jpaboard.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContorller {

    @GetMapping("/")
    public String home(Model model) {
        User user = User.builder().userId("kjstyle").name("이길주").email("kjstyle79@naver.com").build();
        model.addAttribute("user",user);
        return "index";
    }
}