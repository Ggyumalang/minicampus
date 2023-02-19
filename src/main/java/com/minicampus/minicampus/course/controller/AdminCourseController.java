package com.minicampus.minicampus.course.controller;

import com.minicampus.minicampus.admin.service.CategoryService;
import com.minicampus.minicampus.course.dto.CourseDto;
import com.minicampus.minicampus.course.model.CourseInput;
import com.minicampus.minicampus.course.model.CourseParam;
import com.minicampus.minicampus.course.service.CourseService;
import com.minicampus.minicampus.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminCourseController extends BaseController {

    private final CourseService courseService;

    private final CategoryService categoryService;

    @GetMapping("/admin/course/list.do")
    public String list(Model model, CourseParam courseParam) {

        courseParam.init();

        List<CourseDto> courseList = courseService.list(courseParam);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }

        String pagetHtml = getPagerHtml(totalCount, courseParam.getPageSize(),
                courseParam.getPageIndex(), courseParam.getQueryString());

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagetHtml);

        return "admin/course/list";
    }

    @GetMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String add(
            Model model
            , HttpServletRequest request
            , CourseInput parameter
    ) {
        //카테고리 정보를 내려줘야 함.
        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit.do");
        CourseDto detail = new CourseDto();

        if (editMode) {
            Long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                //error처리
                model.addAttribute("errorMessage", "강좌 정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existCourse;
        }
        model.addAttribute("detail", detail);
        model.addAttribute("editMode", editMode);

        return "admin/course/add";
    }

    @PostMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String addSubmit(Model model
            , HttpServletRequest request
            , CourseInput parameter
            , MultipartFile file
    ) {

        String saveFileName = "";
        String urlFileName = "";
        if (file != null) {

            String originalFileName = file.getOriginalFilename();

            String baseLocalPath = "/Users/a/Desktop/Spring파일/minicampus/minicampus/files";
            String baseUrlPath = "/files";
            String[] arrFileName = FileUtil.getNewSaveFile(baseLocalPath, baseUrlPath, originalFileName);

            saveFileName = arrFileName[0];
            urlFileName = arrFileName[1];


            try {
                File newFile = new File(saveFileName);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }

        Boolean editMode = request.getRequestURI().contains("/edit.do");
        parameter.setFileName(saveFileName);
        parameter.setUrlFileName(urlFileName);

        Boolean result = false;
        if (editMode) {
            Long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                //error처리
                model.addAttribute("errorMessage", "강좌 정보가 존재하지 않습니다.");
                return "common/error";
            }
            result = courseService.set(parameter);
        } else {
            result = courseService.add(parameter);
        }

        return "redirect:/admin/course/list.do";
    }

    @PostMapping("/admin/course/delete.do")
    public String del(CourseInput parameter) {
        boolean result = courseService.del(parameter.getIdList());
        return "redirect:/admin/course/list.do";
    }
}
