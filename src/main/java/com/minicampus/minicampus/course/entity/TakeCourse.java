package com.minicampus.minicampus.course.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TakeCourse implements TakeCourseCode{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long courseId;
    private String userId;
    
    private Long payPrice; //결제금액
    private String status; //상태(수강신청, 결제완료, 수강취소)

    //신청일
    private LocalDateTime regDt;
}
