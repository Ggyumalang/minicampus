package com.minicampus.minicampus.course.service.impl;

import com.minicampus.minicampus.course.dto.CourseDto;
import com.minicampus.minicampus.course.entity.Course;
import com.minicampus.minicampus.course.entity.TakeCourse;
import com.minicampus.minicampus.course.mapper.CourseMapper;
import com.minicampus.minicampus.course.model.CourseInput;
import com.minicampus.minicampus.course.model.CourseParam;
import com.minicampus.minicampus.course.model.ServiceResult;
import com.minicampus.minicampus.course.model.TakeCourseInput;
import com.minicampus.minicampus.course.repository.CourseRepository;
import com.minicampus.minicampus.course.repository.TakeCourseRepository;
import com.minicampus.minicampus.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final TakeCourseRepository takeCourseRepository;

    @Override
    public List<CourseDto> list(CourseParam courseParam) {
        long totalCount = courseMapper.selectListCount(courseParam);
        List<CourseDto> list = courseMapper.selectList(courseParam);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 1;
            for (CourseDto dto : list) {
                dto.setTotalCount(totalCount);
                dto.setSeq(courseParam.getPageStart() + i);
                i++;
            }
        }
        return list;
    }

    @Override
    public CourseDto getById(Long id) {
        return courseRepository.findById(id).map(CourseDto::of).orElse(null);
    }

    @Override
    public List<CourseDto> frontList(CourseParam courseParam) {
        //전체일 경우
        if (courseParam.getCategoryId() == null) {
            return courseRepository.findAll().stream()
                    .map(CourseDto::of)
                    .collect(Collectors.toList());
        }

        return courseRepository.findByCategoryId(courseParam.getCategoryId())
                .stream()
                .map(CourseDto::of)
                .collect(Collectors.toList());

    }

    @Override
    public CourseDto frontDetail(Long id) {
        return courseRepository.findById(id).map(CourseDto::of).orElse(null);
    }

    @Override
    public ServiceResult req(TakeCourseInput parameter) {
        Optional<Course> optionalCourse = courseRepository.findById(parameter.getCourseId());
        if (!optionalCourse.isPresent()) {

            return ServiceResult.builder()
                    .result(false)
                    .message("강좌 정보가 존재하지 않습니다.")
                    .build();
        }

        Course course = optionalCourse.get();

        //이미 신청정보가 있는 지 확인
        Long count = takeCourseRepository.countByCourseIdAndUserIdAndStatusIn(
                parameter.getCourseId()
                , parameter.getUserId()
                , List.of(TakeCourse.STATUS_COMPLETE, TakeCourse.STATUS_REQ)
        );

        if (count > 0) {
            return new ServiceResult(false, "이미 신청한 강좌 정보가 존재합니다.");
        }

        takeCourseRepository.save(TakeCourse.builder()
                .courseId(course.getId())
                .userId(parameter.getUserId()) //세션에서 가져온 값이라 필요X
                .payPrice(course.getSalePrice())
                .status(TakeCourse.STATUS_REQ)
                .regDt(LocalDateTime.now())
                .build());
        return new ServiceResult(true, "");
    }

    @Override
    public Boolean del(String idList) {
        if (idList != null && idList.length() > 0) {
            for (String id : idList.split(",")) {
                long _id = 0L;
                try {
                    _id = Long.parseLong(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (_id > 0) {
                    courseRepository.deleteById(_id);
                }
            }

        }
        return true;
    }

    @Override
    public Boolean set(CourseInput parameter) {

        Optional<Course> optionalCourse = courseRepository.findById(parameter.getId());
        if (!optionalCourse.isPresent()) {
            //수정할 데이터가 없음.
            return false;
        }

        Course course = optionalCourse.get();

        course.setCategoryId(parameter.getCategoryId());
        course.setSubject(parameter.getSubject());
        course.setKeyword(parameter.getKeyword());
        course.setSummary(parameter.getSummary());
        course.setContents(parameter.getContents());
        course.setPrice(parameter.getPrice());
        course.setSalePrice(parameter.getSalePrice());
        course.setSaleEndDt(getLocalDate(parameter.getSaleEndDtText()));
        course.setUdtDt(LocalDateTime.now());
        course.setFileName(parameter.getFileName());
        course.setUrlFileName(parameter.getUrlFileName());

        courseRepository.save(course);

        return true;
    }

    @Override
    public Boolean add(CourseInput parameter) {

        courseRepository.save(Course.builder()
                .categoryId(parameter.getCategoryId())
                .subject(parameter.getSubject())
                .keyword(parameter.getKeyword())
                .summary(parameter.getSummary())
                .contents(parameter.getContents())
                .price(parameter.getPrice())
                .salePrice(parameter.getSalePrice())
                .saleEndDt(getLocalDate(parameter.getSaleEndDtText()))
                .regDt(LocalDateTime.now())
                .fileName(parameter.getFileName())
                .urlFileName(parameter.getUrlFileName())
                .build());

        return true;
    }

    @Override
    public List<CourseDto> listAll() {
        return courseRepository.findAll().stream()
                .map(CourseDto::of)
                .collect(Collectors.toList());
    }

    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
