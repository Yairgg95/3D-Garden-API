package com.threedgarden.api.controller;


import com.threedgarden.api.model.Category;
import com.threedgarden.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/categories") //http://localhost:8080/api/categories
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id")Long id){
        try {
            Category deletedCategory = categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(deletedCategory);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategoryById(id, category);
    }
}
