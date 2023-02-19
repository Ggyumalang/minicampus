package com.minicampus.minicampus.course.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInput {
    private Long id;
    private Long categoryId;
    private String keyword;
    private String subject;
    private String summary;
    private String contents;
    private Long price;
    private Long salePrice;
    private String saleEndDtText;

    //삭제를 위한 속성
    private String idList;

    //ADD
    private String fileName;
    private String urlFileName;

}
