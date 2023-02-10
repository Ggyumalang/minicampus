package com.minicampus.minicampus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPage {

    @GetMapping("/")
    public String index() {
        return "Hello !!!";
    }

    @RequestMapping("/hi")
    public String hi(){
        return "hi!!!";
    }
}
