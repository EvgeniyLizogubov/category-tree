package com.github.evgenylizogubov.treeservice.service;

import com.github.evgenylizogubov.treeservice.model.Category;
import com.github.evgenylizogubov.treeservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
    
    public Category add(String parentName, String childName) {
        Category newCategory = new Category();
        newCategory.setName(childName);
        newCategory.setLevel(1);
        
        if (parentName != null) {
            Category parent = categoryRepository.getByName(parentName);
            if (parent == null) {
                return null;
            }
            
            newCategory.setParent(parent);
            newCategory.setLevel(parent.getLevel() + 1);
        }
        
        return categoryRepository.save(newCategory);
    }
    
    public boolean delete(String name) {
        Category category = categoryRepository.getByName(name);
        
        if (category == null) {
            return false;
        }
        
        categoryRepository.delete(category);
        return true;
    }
}
