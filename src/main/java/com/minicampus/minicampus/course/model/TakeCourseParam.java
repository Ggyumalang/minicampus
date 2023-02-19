package com.minicampus.minicampus.course.model;

import com.minicampus.minicampus.admin.model.CommonParam;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TakeCourseParam extends CommonParam {

    private Long id;
    private String status;
    private String userId;
    private Long searchCourseId;
}
