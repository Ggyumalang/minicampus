package com.minicampus.minicampus.admin.mapper;

import com.minicampus.minicampus.admin.dto.BannerDto;
import com.minicampus.minicampus.admin.model.BannerParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    Long selectListCount(BannerParam parameter);

    List<BannerDto> selectList(BannerParam parameter);

    List<BannerDto> selectListPost();
}
