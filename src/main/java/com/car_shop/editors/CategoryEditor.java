package com.car_shop.editors;

import com.car_shop.entity.Category;

import java.beans.PropertyEditorSupport;

public class CategoryEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(String id) throws IllegalArgumentException
    {
        Category category = new Category();
        category.setId(Integer.parseInt(id));
        setValue(category);
    }
}
