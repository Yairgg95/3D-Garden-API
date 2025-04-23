package com.threedgarden.api.service;

import com.threedgarden.api.model.Category;
import com.threedgarden.api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){ return categoryRepository.findAll();}
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La categoría con el id " + id + " no existe")
        );
    }
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    };

    public Category updateCategoryById(Long id, Category newCategory){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) throw new IllegalArgumentException("La categoría con id " + id + " no existe");

        Category category = optionalCategory.get();
        if(newCategory.getName() != null) category.setName(newCategory.getName());
        if(newCategory.getDescription() != null) category.setName(newCategory.getDescription());

        return categoryRepository.save(category);
    }

    public Category deleteCategoryById(Long id) {
        Category tmp = null;
        if (categoryRepository.existsById(id)) {
            tmp = categoryRepository.findById(id).get();
            categoryRepository.deleteById(id);
            return tmp;
        }
        return tmp;
    }
}
