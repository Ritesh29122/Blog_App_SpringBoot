package com.example.BLOG_APP.services;

import com.example.BLOG_APP.payloads.CategoryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface categoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    void deleteCategory(CategoryDto categoryDto, Integer categoryId);
    CategoryDto getCategoryById(CategoryDto categoryDto, Integer categoryId);
    List<CategoryDto>getAllCategories(CategoryDto categoryDto);
}
