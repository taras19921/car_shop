package com.car_shop.service;

import com.car_shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService
{
    void add(Item item, MultipartFile multipartFile) throws Exception;
    void delete(int id);
    void update(Item item, MultipartFile multipartFile);
    Item getOne(int id);
    List<Item> getAll();
    Page<Item> findAllPages(Pageable pageable);
    void update(Item item);
}