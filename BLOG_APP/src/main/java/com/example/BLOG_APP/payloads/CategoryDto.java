package com.example.BLOG_APP.payloads;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "title",length=100,nullable = false)
    private String categoryTitle;

    @Column(name = "description")
    private String categoryDescription;


}
