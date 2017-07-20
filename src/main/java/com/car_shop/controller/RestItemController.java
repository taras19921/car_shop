package com.car_shop.controller;

import com.car_shop.dto.DtoUtilMapper;
import com.car_shop.dto.ItemDto;
import com.car_shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestItemController
{
    private final ItemService itemService;

    @Autowired
    public RestItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/itemSearch")
    public List<ItemDto> loadItems()
    {
        return DtoUtilMapper.itemsToItemsDtos(itemService.getAll());
    }
}
