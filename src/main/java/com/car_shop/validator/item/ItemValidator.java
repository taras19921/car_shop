package com.car_shop.validator.item;

import com.car_shop.dao.ItemDao;
import com.car_shop.entity.Item;
import com.car_shop.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemValidator implements Validator
{
    private final ItemDao itemDao;

    @Autowired
    public ItemValidator(ItemDao itemDao)
    {
        this.itemDao = itemDao;
    }

    @Override
    public void validate(Object o) throws Exception
    {
        Item item = (Item) o;

        if(item.getName().isEmpty())
        {
            throw new ItemException(ItemValidationMessages.EMPTY_ITEMNAME_FIELD);
        }

        else if(itemDao.findByName(item.getName()) != null)
        {
            throw new ItemException(ItemValidationMessages.ITEMNAME_ALREADY_EXIST);
        }

        else if(item.getContent().isEmpty())
        {
            throw new ItemException(ItemValidationMessages.EMPTY_CONTENT_FIELD);
        }

        else if(item.getPrice() == 0)
        {
            throw new ItemException(ItemValidationMessages.ZERO_PRICE_FIELD);
        }
    }
}