package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    private final CategoryRepository categoryrepository;


    public CategoryService(CategoryRepository categoryrepository) {
        this.categoryrepository = categoryrepository;
    }


    public Category addCategory(Category category) {
        return categoryrepository.save(category);
    }


    public Category updateCategory(Long id, Category category) {

        Optional<Category> existCategory = categoryrepository.findById(id);

        if (existCategory.isPresent()) {
            Category existing = existCategory.get();

            existing.setName(category.getName());
            existing.setParent(category.getParent());
            return categoryrepository.save(existing);

        }

        throw new RuntimeException("category not found id " + id);

    }


    public void deleteCategory(Long id) {

        categoryrepository.deleteById(id);

    }


    public Optional<Category> getCategoryById(Long id) {

        return categoryrepository.findById(id);

    }


    public List<Category> getAllCategories() {

        return categoryrepository.findAll();
    }



}




