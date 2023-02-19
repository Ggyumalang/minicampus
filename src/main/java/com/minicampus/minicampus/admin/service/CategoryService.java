package com.minicampus.minicampus.admin.service;

import com.minicampus.minicampus.admin.dto.CategoryDto;
import com.minicampus.minicampus.admin.model.CategoryInput;

import java.util.List;

public interface CategoryService {

    /**
     * 전체 카테고리 정보 가져오기
     *
     * @return
     */
    List<CategoryDto> list();

    /**
     * 카테고리 신규 추가
     *
     * @param categoryName
     * @return
     */
    Boolean add(String categoryName);

    /**
     * 카테고리 수정
     *
     * @param parameter
     * @return
     */
    Boolean update(CategoryInput parameter);

    /**
     * 카테고리 삭제
     *
     * @param id
     * @return
     */
    Boolean del(long id);

    /**
     * 프론트 카테고리 정보
     *
     * @param parameter
     * @return
     */
    List<CategoryDto> frontList(CategoryDto parameter);
}
