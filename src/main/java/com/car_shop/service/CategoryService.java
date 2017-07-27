package com.car_shop.service;

import com.car_shop.entity.Brand;

import java.util.List;

public interface CategoryService
{
    void add(Brand brand);
    void delete(int id);
    void update(String categoryInfo);
    Brand getOne(int id);
    List<Brand> getAll();
}
