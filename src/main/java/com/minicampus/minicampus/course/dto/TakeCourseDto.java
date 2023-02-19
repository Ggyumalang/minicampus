package com.minicampus.minicampus.course.dto;

import com.minicampus.minicampus.course.entity.Course;
import com.minicampus.minicampus.course.entity.TakeCourse;
import lombok.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TakeCourseDto {

    private Long id;

    private Long courseId;
    private String userId;

    private Long payPrice; //결제금액
    private String status; //상태(수강신청, 결제완료, 수강취소)

    //신청일
    private LocalDateTime regDt;

    //Join 컬럼
    String subject;
    String userName;
    String phone;

    //추가컬럼
    Long totalCount;
    Long seq;
    public static TakeCourseDto of(TakeCourse takeCourse) {
        return TakeCourseDto.builder()
                .id(takeCourse.getId())
                .courseId(takeCourse.getCourseId())
                .userId(takeCourse.getUserId())
                .payPrice(takeCourse.getPayPrice())
                .status(takeCourse.getStatus())
                .regDt(takeCourse.getRegDt())
                .build();
    }

    public String getRegDtText(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(dateTimeFormatter) : "";
    }
}
