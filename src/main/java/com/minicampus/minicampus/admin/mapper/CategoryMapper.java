package com.minicampus.minicampus.admin.mapper;

import com.minicampus.minicampus.admin.dto.CategoryDto;
import com.minicampus.minicampus.admin.dto.MemberDto;
import com.minicampus.minicampus.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDto> select(CategoryDto parameter);
}
