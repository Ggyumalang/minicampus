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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long categoryId;

    private String imagePath;
    private String keyword;
    @Column(unique = true)
    private String subject;

    @Column(length = 1000)
    private String summary;

    //양이 많은 텍스트..
    @Lob
    private String contents;

    private Long price;
    private Long salePrice;
    private LocalDate saleEndDt;

    //등록일
    private LocalDateTime regDt;
    //수정일
    private LocalDateTime udtDt;

    private String fileName;
    private String urlFileName;
}
