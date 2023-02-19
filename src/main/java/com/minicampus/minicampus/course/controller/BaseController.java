package com.minicampus.minicampus.course.controller;

import com.minicampus.minicampus.course.dto.CourseDto;
import com.minicampus.minicampus.util.PageUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class BaseController {

    public String getPagerHtml(Long totalCount, Long pageSize, Long pageIndex, String queryString){

        PageUtil pageUtil = new PageUtil(totalCount
                , pageSize
                , pageIndex
                , queryString);

        return pageUtil.pager();

    }
}
