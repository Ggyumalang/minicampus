package com.minicampus.minicampus.course.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResult {
    Boolean result;
    String message;
}
