package com.minicampus.minicampus.member.controller;

import com.minicampus.minicampus.common.model.ResponseResult;
import com.minicampus.minicampus.common.model.ResponseResultHeader;
import com.minicampus.minicampus.course.dto.TakeCourseDto;
import com.minicampus.minicampus.course.model.ServiceResult;
import com.minicampus.minicampus.course.model.TakeCourseInput;
import com.minicampus.minicampus.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ApiMemberController {
    private final TakeCourseService takeCourseService;

    @PostMapping("/api/member/course/cancel.api")
    public ResponseEntity<?> cancelCourse(
            Model model
            , @RequestBody TakeCourseInput parameter
            , Principal principal
    ) {
        //내 강좌인 지 확인
        TakeCourseDto takeCourseDto = takeCourseService.detail(parameter.getTakeCourseId());
        if (takeCourseDto == null) {
            return ResponseEntity.ok().body(new ResponseResult(false, "수강 신청 정보가 존재하지 않습니다."));
        }
        String userId = principal.getName();
        if (userId == null || userId.equals("") || !takeCourseDto.getUserId().equals(userId)) {
            return ResponseEntity.ok().body(new ResponseResult(false, "본인의 수강 신청 정보만 취소할 수 있습니다."));
        }
        
        //수강 신청 취소 처리
        ServiceResult result = takeCourseService.cancel(parameter.getTakeCourseId());

        if (!result.getResult()) {
            return ResponseEntity.ok().body(new ResponseResult(false, result.getMessage()));
        }

        return ResponseEntity.ok().body(new ResponseResult(true));
    }

}
