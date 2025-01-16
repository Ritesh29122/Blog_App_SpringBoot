package com.example.BLOG_APP.controller;

import com.example.BLOG_APP.payloads.CategoryDto;
import com.example.BLOG_APP.services.categoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class categoryController {

    @Autowired
    categoryService categoryService;


    //GET
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>getAllCategories(@Valid @RequestBody CategoryDto categoryDto){
        List<CategoryDto> categoryDto1=this.categoryService.getAllCategories(categoryDto);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>getCategoryById(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId)
    {
        CategoryDto categoryDto1=this.categoryService.getCategoryById(categoryDto,categoryId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }


    //POST
    @PostMapping("/")
    public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1= this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    //POST update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId){
        CategoryDto categoryDto1=this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>deleteCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId){
        this.categoryService.deleteCategory(categoryDto,categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
