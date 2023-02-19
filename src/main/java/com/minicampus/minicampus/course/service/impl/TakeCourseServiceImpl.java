package com.minicampus.minicampus.course.service.impl;

import com.minicampus.minicampus.course.dto.TakeCourseDto;
import com.minicampus.minicampus.course.entity.TakeCourse;
import com.minicampus.minicampus.course.entity.TakeCourseCode;
import com.minicampus.minicampus.course.mapper.TakeCourseMapper;
import com.minicampus.minicampus.course.model.ServiceResult;
import com.minicampus.minicampus.course.model.TakeCourseParam;
import com.minicampus.minicampus.course.repository.TakeCourseRepository;
import com.minicampus.minicampus.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TakeCourseServiceImpl implements TakeCourseService {

    private final TakeCourseRepository takeCourseRepository;
    private final TakeCourseMapper takeCourseMapper;

    @Override
    public List<TakeCourseDto> list(TakeCourseParam parameter) {
        Long totalCount = takeCourseMapper.selectListCount(parameter);
        List<TakeCourseDto> list = takeCourseMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 1;
            for (TakeCourseDto dto : list) {
                dto.setTotalCount(totalCount);
                dto.setSeq(parameter.getPageStart() + i);
                i++;
            }
        }
        return list;
    }

    @Override
    public ServiceResult updateStatus(Long id, String status) {
        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        if (!optionalTakeCourse.isPresent()) {
            return new ServiceResult(false, "수강정보가 존재하지 않습니다.");
        }

        TakeCourse takeCourse = optionalTakeCourse.get();
        takeCourse.setStatus(status);
        takeCourseRepository.save(takeCourse);


        return new ServiceResult(true, "");
    }

    @Override
    public List<TakeCourseDto> myCourse(String userId) {
        return takeCourseMapper.selectListMyCourse(TakeCourseParam.builder()
                .userId(userId)
                .build()
        );
    }

    @Override
    public ServiceResult cancel(Long id) {
        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        if (!optionalTakeCourse.isPresent()) {
            return new ServiceResult(false, "수강정보가 존재하지 않습니다.");
        }

        TakeCourse takeCourse = optionalTakeCourse.get();
        takeCourse.setStatus(TakeCourse.STATUS_CANCEL);
        takeCourseRepository.save(takeCourse);

        return new ServiceResult(true, "");
    }

    @Override
    public TakeCourseDto detail(Long id) {

        return takeCourseRepository.findById(id)
                .map(TakeCourseDto::of).orElse(null);
    }
}
