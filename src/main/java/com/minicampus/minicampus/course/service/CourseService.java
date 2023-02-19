package com.minicampus.minicampus.course.service;

import com.minicampus.minicampus.course.dto.CourseDto;
import com.minicampus.minicampus.course.model.CourseInput;
import com.minicampus.minicampus.course.model.CourseParam;
import com.minicampus.minicampus.course.model.ServiceResult;
import com.minicampus.minicampus.course.model.TakeCourseInput;

import java.util.List;

public interface CourseService {

    /**
     * 강좌 등록
     * @param parameter
     * @return
     */
    Boolean add(CourseInput parameter);

    /**
     * 강좌 정보 수정
     * @param parameter
     * @return
     */
    Boolean set(CourseInput parameter);

    /**
     * 강좌 목록 조회
     * @param courseParam
     * @return
     */
    List<CourseDto> list(CourseParam courseParam);

    /**
     * 강좌 상세 정보
     * @param id
     * @return
     */
    CourseDto getById(Long id);

    /**
     * 강좌 내용 삭제
     * @param idList
     * @return
     */
    Boolean del(String idList);

    /**
     * 프론트 강좌 목록
     */
    List<CourseDto> frontList(CourseParam courseParam);

    /**
     * 프론트 강좌 상세 정보
     * 프론트 = 회원이 보는 화면
     * @param id
     * @return
     */
    CourseDto frontDetail(Long id);

    /**
     * 수강 신청
     */
    ServiceResult req(TakeCourseInput parameter);

    /**
     * 전체 강좌 목록
     * @return
     */
    List<CourseDto> listAll();
}
