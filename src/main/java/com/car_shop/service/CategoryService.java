package com.car_shop.service;

import com.car_shop.entity.Category;

import java.util.List;

public interface CategoryService
{
    void add(Category category);
    void delete(int id);
    void update(String categoryInfo);
    Category getOne(int id);
    List<Category> getAll();
}
