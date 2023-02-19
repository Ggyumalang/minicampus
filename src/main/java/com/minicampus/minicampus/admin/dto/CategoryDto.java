package com.minicampus.minicampus.admin.dto;

import com.minicampus.minicampus.admin.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long id;

    private String categoryName;

    private Integer sortValue;

    Boolean usingYn;

    //추가된 컬럼
    Integer courseCount;

    public static CategoryDto of(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .sortValue(category.getSortValue())
                .usingYn(category.getUsingYn())
                .build();
    }
}
