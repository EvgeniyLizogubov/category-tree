package com.github.evgenylizogubov.treeservice.service;

import com.github.evgenylizogubov.treeservice.dto.CategoryDto;
import com.github.evgenylizogubov.treeservice.model.Category;
import com.github.evgenylizogubov.treeservice.repository.CategoryRepository;
import com.github.evgenylizogubov.treeservice.util.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::mapToCategoryDto)
                .toList();
    }
    
    public CategoryDto add(String parentName, String newCategoryName) {
        Category newCategory = new Category();
        newCategory.setName(newCategoryName);
        newCategory.setLevel(1);
        
        if (parentName != null) {
            Category parent = categoryRepository.getByName(parentName);
            if (parent == null) {
                return null;
            }
            
            newCategory.setParent(parent);
            newCategory.setLevel(parent.getLevel() + 1);
        }
        
        Category saved = categoryRepository.save(newCategory);
        return CategoryMapper.mapToCategoryDto(saved);
    }
    
    public int delete(String categoryName) {
        Category category = categoryRepository.getByName(categoryName);
        
        if (category == null) {
            return 0;
        }
        
        return categoryRepository.deleteByName(categoryName);
    }
}
