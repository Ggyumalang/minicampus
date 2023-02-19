package com.minicampus.minicampus.course.controller;

import com.minicampus.minicampus.admin.dto.CategoryDto;
import com.minicampus.minicampus.admin.service.BannerService;
import com.minicampus.minicampus.admin.service.CategoryService;
import com.minicampus.minicampus.course.dto.CourseDto;
import com.minicampus.minicampus.course.model.CourseParam;
import com.minicampus.minicampus.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CourseController extends BaseController{
    private final CourseService courseService;
    private final CategoryService categoryService;

    private final BannerService bannerService;

    @GetMapping("/course")
    public String course(
            Model model
            , CourseParam courseParam
    ){

        List<CourseDto> list = courseService.frontList(courseParam);
        model.addAttribute("list" , list);

        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        model.addAttribute("categoryList", categoryList);

        int courseTotalCount = 0;
        if(categoryList != null){
           courseTotalCount = categoryList.stream().mapToInt(CategoryDto::getCourseCount).reduce(0, Integer::sum);
        }

        model.addAttribute("courseTotalCount", courseTotalCount);

        return "course/index";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(
            Model model
            , CourseParam parameter
    ){

        CourseDto detail = courseService.frontDetail(parameter.getId());
        model.addAttribute("detail", detail);

        return "course/detail";
    }
}
