package com.minicampus.minicampus.admin.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryInput {

    private Long id;
    private String categoryName;
    private Integer sortValue;
    private Boolean usingYn;
}
