package com.github.evgenylizogubov.treeservice.controller;

import com.github.evgenylizogubov.treeservice.model.Category;
import com.github.evgenylizogubov.treeservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }
    
    @PostMapping
    public Category add(@RequestParam(required = false) String parentName, @RequestParam String childName) {
        return categoryService.add(parentName, childName);
    }
    
    @DeleteMapping
    public boolean remove(@RequestParam String name) {
        return categoryService.delete(name);
    }
}
