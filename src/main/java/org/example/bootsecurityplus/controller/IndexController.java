package org.example.bootsecurityplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("msg", "로그인이 아닙니다");
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("msg", "어드민입니다");
        return "index";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("msg", "유저입니다");
        return "index";
    }
}
