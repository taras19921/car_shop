package com.car_shop.editors;

import com.car_shop.entity.Item;

import java.beans.PropertyEditorSupport;

public class ItemEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(String id) throws IllegalArgumentException
    {
        Item item = new Item();
        item.setId(Integer.parseInt(id));
        setValue(item);
    }
}
