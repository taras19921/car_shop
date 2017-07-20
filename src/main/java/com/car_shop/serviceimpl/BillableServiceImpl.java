package com.car_shop.serviceimpl;

import com.car_shop.dao.BillableDao;
import com.car_shop.dao.ItemDao;
import com.car_shop.dao.UserDao;
import com.car_shop.entity.Billable;
import com.car_shop.entity.Item;
import com.car_shop.entity.User;
import com.car_shop.service.BillableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class BillableServiceImpl implements BillableService
{
    private final BillableDao billableDao;

    private final UserDao userDao;

    private final ItemDao itemDao;

    @Autowired
    public BillableServiceImpl(BillableDao billableDao, UserDao userDao, ItemDao itemDao)
    {
        this.billableDao = billableDao;
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    public void add(Billable billable)
    {
        billableDao.save(billable);
    }

    public void delete(int id)
    {
        billableDao.delete(id);
    }

    public void update(Billable billable)
    {
        billableDao.save(billable);
    }

    public Billable getOne(int id)
    {
        return billableDao.findOne(id);
    }

    public List<Billable> getAll()
    {
        return billableDao.findAll();
    }

    public Set<Billable> getBillableWithItems()
    {
        return billableDao.getBillableWithItems();
    }

    @Override
    public void addToCart(Principal principal, int id)
    {
        User user = userDao.userWithItems(Integer.parseInt(principal.getName()));
        Item item = itemDao.getOne(id);
        user.getItems().add(item);
        userDao.save(user);
    }

    @Override
    public void deleteFromCart(int userId, int itemId)
    {
        User user = userDao.userWithItems(userId);
        Item item = itemDao.itemWithUsers(itemId);
        user.getItems().remove(item);
        userDao.save(user);
    }

    @Override
    public void buy(int userId)
    {
        Billable billable = new Billable(LocalDateTime.now());
        billableDao.saveAndFlush(billable);
        User user = userDao.userWithItems(userId);
        billable.setUser(user);
        for (Item item: user.getItems())
        {
            billable.getItem().add(item);
            billableDao.save(billable);
        }

        user.getItems().clear();
        userDao.save(user);
    }

    @Override
    public void getTotalPrice(int id)
    {
        Billable billable = billableDao.getOne(id);
        int price = 0 ;
        for (Item item: billable.getItem())
        {
            price += item.getPrice() * item.getQuantity();
            billable.setPrice(price);
        }
    }

    @Override
    public void makeSleep()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Page<Billable> findAllPages(Pageable pageable)
    {
        return billableDao.findAll(pageable);
    }
}