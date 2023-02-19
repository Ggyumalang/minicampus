package com.minicampus.minicampus.main.controller;

import com.minicampus.minicampus.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BannerService bannerService;

    @GetMapping("/")
    public String index(
            Model model
    ) {
        model.addAttribute("list", bannerService.frontList());
        return "index";
    }

    @ResponseBody
    @GetMapping("/images")
    public Resource showImage(String filePath) throws MalformedURLException {
        System.out.println(filePath);
        return new UrlResource("file:"+ filePath.substring(1));
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }

}
