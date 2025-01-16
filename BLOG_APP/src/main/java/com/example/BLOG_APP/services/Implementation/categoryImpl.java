package com.example.BLOG_APP.services.Implementation;

import com.example.BLOG_APP.Exceptions.ResourceNotFoundException;
import com.example.BLOG_APP.models.Category;
import com.example.BLOG_APP.payloads.CategoryDto;
import com.example.BLOG_APP.payloads.UserDto;
import com.example.BLOG_APP.repositories.UserRepo;
import com.example.BLOG_APP.repositories.categoryRepo;
import com.example.BLOG_APP.services.categoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class categoryImpl implements categoryService {

    @Autowired
    private categoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category= this.CategoryDTOToCategory(categoryDto);
        System.out.println(category.getCategoryTitle());
        Category savedCategory = this.categoryRepo.save(category);
        return this.CategoryToCategoryDTO(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category= this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory=this.categoryRepo.save(category);
        return this.CategoryToCategoryDTO(updatedCategory);
    }
    @Override
    public void deleteCategory(CategoryDto categoryDto, Integer categoryID) {
        Category category = this.categoryRepo.findById(categoryID).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryID));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        return this.CategoryToCategoryDTO(category);
    }

    @Override
    public List<CategoryDto> getAllCategories(CategoryDto categoryDto) {
        List<Category> categoryList=this.categoryRepo.findAll();
        List<CategoryDto>categoryDtoList=categoryList.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }

    public CategoryDto CategoryToCategoryDTO(Category category){
        return this.modelMapper.map(category, CategoryDto.class);
    }

    public Category CategoryDTOToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto,Category.class);
    }
}
