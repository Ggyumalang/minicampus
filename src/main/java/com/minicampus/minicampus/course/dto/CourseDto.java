package com.minicampus.minicampus.course.dto;

import com.minicampus.minicampus.course.entity.Course;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private Long id;
    private Long categoryId;

    private String imagePath;
    private String keyword;
    private String subject;

    private String summary;

    private String contents;

    private Long price;
    private Long salePrice;
    private LocalDate saleEndDt;

    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    private String fileName;
    private String urlFileName;

    //추가 컬럼
    private Long totalCount;
    private Long seq;

    public static CourseDto of(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .categoryId(course.getCategoryId())
                .imagePath(course.getImagePath())
                .keyword(course.getKeyword())
                .subject(course.getSubject())
                .summary(course.getSummary())
                .contents(course.getContents())
                .price(course.getPrice())
                .salePrice(course.getSalePrice())
                .saleEndDt(course.getSaleEndDt())
                .regDt(course.getRegDt())
                .udtDt(course.getUdtDt())
                .fileName(course.getFileName())
                .urlFileName(course.getUrlFileName())
                .build();
    }

    public String getRegDtText() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(dateTimeFormatter) : "";
    }
}
