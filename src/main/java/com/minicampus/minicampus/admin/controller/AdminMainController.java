package com.minicampus.minicampus.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminMainController {

    @GetMapping("/admin/main.do")
    public String main() {
        return "admin/main";
    }
}
