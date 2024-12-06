package com.github.evgenylizogubov.treeservice.util;

import com.github.evgenylizogubov.treeservice.dto.CategoryDto;
import com.github.evgenylizogubov.treeservice.model.Category;

public class CategoryMapper {
    public static Category mapToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }
    
    public static CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setLevel(category.getLevel());
        return categoryDto;
    }
}
