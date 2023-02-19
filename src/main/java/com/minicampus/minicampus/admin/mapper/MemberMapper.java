package com.minicampus.minicampus.admin.mapper;

import com.minicampus.minicampus.admin.dto.MemberDto;
import com.minicampus.minicampus.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    Long selectListCount(MemberParam parameter);
    List<MemberDto> selectList(MemberParam parameter);




}
