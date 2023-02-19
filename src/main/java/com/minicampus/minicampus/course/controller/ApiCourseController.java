package com.minicampus.minicampus.course.controller;


import com.minicampus.minicampus.common.model.ResponseResult;
import com.minicampus.minicampus.common.model.ResponseResultHeader;
import com.minicampus.minicampus.course.model.ServiceResult;
import com.minicampus.minicampus.course.model.TakeCourseInput;
import com.minicampus.minicampus.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ApiCourseController {

    private final CourseService courseService;

    @PostMapping("/api/course/req.api")
    public ResponseEntity<?> courseReq(
            Model model
            , @RequestBody TakeCourseInput parameter
            , Principal principal
    ) {
        //로그인한 Id
        parameter.setUserId(principal.getName());

        ServiceResult result = courseService.req(parameter);

        if (!result.getResult()) {
            return ResponseEntity.ok().body(new ResponseResult(false, result.getMessage()));
        }

        return ResponseEntity.ok().body(new ResponseResult(true));
    }
}
