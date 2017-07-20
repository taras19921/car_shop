package com.car_shop.controller;

import com.car_shop.dto.CategoryDto;
import com.car_shop.dto.DtoUtilMapper;
import com.car_shop.entity.Category;
import com.car_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController
{
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public List<CategoryDto> loadCategories()
    {
        return DtoUtilMapper.categoriesToCategoriesDtos(categoryService.getAll());
    }

    @PostMapping("/category")
    public List<CategoryDto> addCategory(@RequestBody Category category)
    {
        categoryService.add(category);
        return DtoUtilMapper.categoriesToCategoriesDtos(categoryService.getAll());
    }

    @DeleteMapping("/category")
    public List<CategoryDto> deleteCategory(@RequestBody String categoryId)
    {
        categoryService.delete(Integer.valueOf(categoryId));
        return DtoUtilMapper.categoriesToCategoriesDtos(categoryService.getAll());
    }

    @PutMapping("/category")
    public List<CategoryDto> updateCategory(@RequestBody String categoryInfo)
    {
        categoryService.update(categoryInfo);
        return DtoUtilMapper.categoriesToCategoriesDtos(categoryService.getAll());
    }
}
