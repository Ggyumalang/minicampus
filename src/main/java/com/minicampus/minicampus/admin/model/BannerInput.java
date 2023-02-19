package com.minicampus.minicampus.admin.model;

import com.minicampus.minicampus.admin.entity.Banner;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerInput {
    private Long id;
    private String subject;
    private String clickedPath;
    private String openMethod;
    private String sortValue;
    private Boolean postYn;
    private Long courseId;

    //삭제를 위한 속성
    private String idList;

    //ADD
    private String fileName;
    private String urlFileName;
}
