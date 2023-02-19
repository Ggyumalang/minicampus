package com.minicampus.minicampus.course.model;

import com.minicampus.minicampus.admin.model.CommonParam;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseParam extends CommonParam {
    Long id;
    Long categoryId;
}
