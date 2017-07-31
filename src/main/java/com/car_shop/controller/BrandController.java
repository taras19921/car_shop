package com.car_shop.controller;

import com.car_shop.dto.BrandDto;
import com.car_shop.dto.DtoUtilMapper;
import com.car_shop.entity.Brand;
import com.car_shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController
{
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brand")
    public List<BrandDto> loadCategories()
    {
        return DtoUtilMapper.categoriesToCategoriesDtos(brandService.getAll());
    }

    @PostMapping("/brand")
    public List<BrandDto> addCategory(@RequestBody Brand brand)
    {
        brandService.add(brand);
        return DtoUtilMapper.categoriesToCategoriesDtos(brandService.getAll());
    }

    @DeleteMapping("/brand")
    public List<BrandDto> deleteCategory(@RequestBody String categoryId)
    {
        brandService.delete(Integer.valueOf(categoryId));
        return DtoUtilMapper.categoriesToCategoriesDtos(brandService.getAll());
    }

    @PutMapping("/brand")
    public List<BrandDto> updateCategory(@RequestBody String categoryInfo)
    {
        brandService.update(categoryInfo);
        return DtoUtilMapper.categoriesToCategoriesDtos(brandService.getAll());
    }
}
