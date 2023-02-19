package com.minicampus.minicampus.course.mapper;

import com.minicampus.minicampus.course.dto.TakeCourseDto;
import com.minicampus.minicampus.course.model.TakeCourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeCourseMapper {
    Long selectListCount(TakeCourseParam parameter);

    List<TakeCourseDto> selectList(TakeCourseParam parameter);

    List<TakeCourseDto> selectListMyCourse(TakeCourseParam parameter);

}
