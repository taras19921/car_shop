package com.car_shop.serviceimpl;

import com.car_shop.dao.CategoryDao;
import com.car_shop.dao.ItemDao;
import com.car_shop.entity.Category;
import com.car_shop.entity.Item;
import com.car_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private final CategoryDao categoryDao;

    private final ItemDao itemDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, ItemDao itemDao)
    {
        this.categoryDao = categoryDao;
        this.itemDao = itemDao;
    }

    public void add(Category category){
        categoryDao.save(category);
    }

    public void delete(int id)
    {
        Category category = categoryDao.categoryWithItems(id);

        for (Item item : category.getItem())
        {
            item.setCategory(null);
            itemDao.saveAndFlush(item);
        }
        categoryDao.delete(id);
    }

    public void update(String categoryInfo)
    {
        Category category = categoryDao.findOne(Integer.parseInt(categoryInfo.split("_")[1]));
        category.setName(categoryInfo.split("_")[0]);
        categoryDao.save(category);
    }

    public Category getOne(int id)
    {
        return categoryDao.findOne(id);
    }

    public List<Category> getAll()
    {
        return categoryDao.findAll();
    }
}