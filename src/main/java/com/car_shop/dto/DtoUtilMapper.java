package com.car_shop.dto;

import com.car_shop.entity.Category;
import com.car_shop.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class DtoUtilMapper
{

    public static ItemDto itemToItemDto(Item item)
    {
        return new ItemDto(item.getId(), item.getName(), item.getContent(), item.getPrice(), item.getPathImage(), categoryWithoutItems(item.getCategory()));
    }

    public static Category categoryWithoutItems(Category category)
    {
        return new Category(category.getName());
    }


    public static List<CategoryDto> categoriesToCategoriesDtos(List<Category> categories)
    {
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();

        for (Category category : categories)
        {
            categoryDtos.add(new CategoryDto(category.getId(), category.getName()));
        }
        return categoryDtos;
    }

    public static List<ItemDto> itemsToItemsDtos(List<Item> items)
    {
        List<ItemDto> itemDtos = new ArrayList<>();

        for (Item item : items)
        {
            ItemDto itemDto = itemToItemDto(item);
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }
}
