package com.minicampus.minicampus.course.service;

import com.minicampus.minicampus.course.dto.TakeCourseDto;
import com.minicampus.minicampus.course.model.ServiceResult;
import com.minicampus.minicampus.course.model.TakeCourseParam;

import java.util.List;

public interface TakeCourseService {

    /**
     * 수강 목록
     */
    List<TakeCourseDto> list(TakeCourseParam parameter);

    /**
     * 수강 상태 변경
     *
     * @param id
     * @param status
     * @return
     */
    ServiceResult updateStatus(Long id, String status);

    /**
     * 내 수강내역 목록
     *
     * @param userId
     * @return
     */
    List<TakeCourseDto> myCourse(String userId);

    /**
     * 수강 상세 정보
     *
     * @param id
     * @return
     */
    TakeCourseDto detail(Long id);

    /**
     * 수강신청 취소
     *
     * @param id
     * @return
     */
    ServiceResult cancel(Long id);
}
