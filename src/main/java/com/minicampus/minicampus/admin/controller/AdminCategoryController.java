package com.minicampus.minicampus.admin.controller;

import com.minicampus.minicampus.admin.dto.CategoryDto;
import com.minicampus.minicampus.admin.model.CategoryInput;
import com.minicampus.minicampus.admin.model.MemberParam;
import com.minicampus.minicampus.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/admin/category/list.do")
    public String list(Model model, MemberParam memberParam) {
        List<CategoryDto> list = categoryService.list();
        model.addAttribute("list", list);

        return "admin/category/list";
    }

    @PostMapping("/admin/category/add.do")
    public String add(Model model, CategoryInput parameter) {

        Boolean result = categoryService.add(parameter.getCategoryName());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/delete.do")
    public String del(Model model, CategoryInput parameter) {

        Boolean result = categoryService.del(parameter.getId());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/update.do")
    public String update(Model model, CategoryInput parameter) {

        Boolean result = categoryService.update(parameter);

        return "redirect:/admin/category/list.do";
    }
}
