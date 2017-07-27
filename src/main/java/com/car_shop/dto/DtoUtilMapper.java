package com.car_shop.dto;

import com.car_shop.entity.Brand;
import com.car_shop.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class DtoUtilMapper
{

    public static CarDto itemToItemDto(Car car)
    {
        return new CarDto(car.getId(), car.getName(), car.getContent(), car.getPrice(), car.getPathImage(), categoryWithoutItems(car.getBrand()));
    }

    public static Brand categoryWithoutItems(Brand brand)
    {
        return new Brand(brand.getName());
    }


    public static List<BrandDto> categoriesToCategoriesDtos(List<Brand> categories)
    {
        List<BrandDto> brandDtos = new ArrayList<BrandDto>();

        for (Brand brand : categories)
        {
            brandDtos.add(new BrandDto(brand.getId(), brand.getName()));
        }
        return brandDtos;
    }

    public static List<CarDto> itemsToItemsDtos(List<Car> cars)
    {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : cars)
        {
            CarDto carDto = itemToItemDto(car);
            carDtos.add(carDto);
        }
        return carDtos;
    }
}
