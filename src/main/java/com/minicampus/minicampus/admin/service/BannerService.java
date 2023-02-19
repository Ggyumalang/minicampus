package com.minicampus.minicampus.admin.service;

import com.minicampus.minicampus.admin.dto.BannerDto;
import com.minicampus.minicampus.admin.model.BannerInput;
import com.minicampus.minicampus.admin.model.BannerParam;
import com.minicampus.minicampus.course.model.ServiceResult;

import java.util.List;

public interface BannerService {


    /**
     * 배너 등록
     *
     * @param parameter
     * @return
     */
    ServiceResult add(BannerInput parameter);

    /**
     * 배너 정보 수정
     *
     * @param parameter
     * @return
     */
    ServiceResult set(BannerInput parameter);

    /**
     * 배너 목록 조회
     *
     * @param courseParam
     * @return
     */
    List<BannerDto> list(BannerParam courseParam);

    /**
     * 배너 상세 정보
     *
     * @param id
     * @return
     */
    BannerDto getById(Long id);

    /**
     * 배너 내용 삭제
     *
     * @param idList
     * @return
     */
    Boolean del(String idList);

    /**
     * 메인 화면에 보여질 배너들
     */
    List<BannerDto> frontList();

}
