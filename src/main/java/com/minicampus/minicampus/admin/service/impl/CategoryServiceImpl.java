package com.minicampus.minicampus.admin.service.impl;

import com.minicampus.minicampus.admin.dto.CategoryDto;
import com.minicampus.minicampus.admin.entity.Category;
import com.minicampus.minicampus.admin.mapper.CategoryMapper;
import com.minicampus.minicampus.admin.model.CategoryInput;
import com.minicampus.minicampus.admin.repository.CategoryRepository;
import com.minicampus.minicampus.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> list() {
        List<Category> categories = categoryRepository.findAllByOrderBySortValueDesc();
        if (CollectionUtils.isEmpty(categories)) {
            return null;
        }
        return categories.stream()
                .map(CategoryDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean add(String categoryName) {

        //카테고리명이 중복인 것 체크하기
//        categoryRepository.findByCategoryName

        categoryRepository.save(Category.builder()
                .categoryName(categoryName)
                .sortValue(0)
                .usingYn(true)
                .build());
        return true;
    }

    @Override
    public Boolean update(CategoryInput parameter) {
        Optional<Category> optionalCategory = categoryRepository.findById(parameter.getId());
        if (!optionalCategory.isPresent()) {
            return false;
        }

        Category category = optionalCategory.get();
        category.setCategoryName(parameter.getCategoryName());
        category.setSortValue(parameter.getSortValue());
        category.setUsingYn(parameter.getUsingYn());
        categoryRepository.save(category);

        return true;
    }

    @Override
    public Boolean del(long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CategoryDto> frontList(CategoryDto parameter) {
        return categoryMapper.select(parameter);
    }
}
