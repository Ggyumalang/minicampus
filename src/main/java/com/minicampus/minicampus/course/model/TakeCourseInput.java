package com.minicampus.minicampus.course.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TakeCourseInput {

    private Long takeCourseId;
    private Long courseId;
    private String userId;


}
