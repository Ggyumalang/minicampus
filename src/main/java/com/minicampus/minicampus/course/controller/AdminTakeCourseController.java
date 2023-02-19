package com.minicampus.minicampus.course.controller;

import com.minicampus.minicampus.course.dto.CourseDto;
import com.minicampus.minicampus.course.dto.TakeCourseDto;
import com.minicampus.minicampus.course.model.ServiceResult;
import com.minicampus.minicampus.course.model.TakeCourseParam;
import com.minicampus.minicampus.course.service.CourseService;
import com.minicampus.minicampus.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminTakeCourseController extends BaseController {
    private final CourseService courseService;
    private final TakeCourseService takeCourseService;

    @GetMapping("/admin/takecourse/list.do")
    public String list(Model model
            , TakeCourseParam takeCourseParam
            , BindingResult bindingResult
    ) {
        takeCourseParam.init();

        List<TakeCourseDto> takeCourseList = takeCourseService.list(takeCourseParam);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(takeCourseList)) {
            totalCount = takeCourseList.get(0).getTotalCount();
        }

        String pagetHtml = getPagerHtml(totalCount, takeCourseParam.getPageSize(),
                takeCourseParam.getPageIndex(), takeCourseParam.getQueryString());

        model.addAttribute("list", takeCourseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagetHtml);

        List<CourseDto> courseList = courseService.listAll();
        model.addAttribute("courseList", courseList);

        return "admin/takecourse/list";
    }

    @PostMapping("/admin/takecourse/status.do")
    public String status(Model model, TakeCourseParam takeCourseParam) {
        ServiceResult serviceResult = takeCourseService.updateStatus(takeCourseParam.getId()
                , takeCourseParam.getStatus());

        if (!serviceResult.getResult()) {
            model.addAttribute("errorMessage", serviceResult.getMessage());
            return "common/error";
        }
        return "redirect:/admin/takecourse/list.do";
    }
}
