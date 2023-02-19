package com.minicampus.minicampus.course.mapper;

import com.minicampus.minicampus.course.dto.CourseDto;
import com.minicampus.minicampus.course.model.CourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    Long selectListCount(CourseParam parameter);
    List<CourseDto> selectList(CourseParam parameter);
}
