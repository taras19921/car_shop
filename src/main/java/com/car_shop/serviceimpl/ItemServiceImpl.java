package com.car_shop.serviceimpl;

import com.car_shop.dao.ItemDao;
import com.car_shop.entity.Item;
import com.car_shop.service.ItemService;
import com.car_shop.validator.Validator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService
{
    private final ItemDao itemDao;

    private final Validator validator;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao, @Qualifier("itemValidator") Validator validator)
    {
        this.itemDao = itemDao;
        this.validator = validator;
    }

    public void add(Item item, MultipartFile multipartFile) throws Exception
    {
        validator.validate(item);
        String path = "E:" + File.separator + "Downloads" + File.separator + "apache-tomcat-8.0.43" + File.separator + "resources" + File.separator +
                item.getName() + File.separator + multipartFile.getOriginalFilename();

        item.setPathImage("resources" + File.separator + item.getName() + File.separator + multipartFile.getOriginalFilename());

        File file = new File(path);

        if(!file.exists())
        {
            file.mkdirs();
        }
        try
        {
            multipartFile.transferTo(file);
        }
        catch (IOException e)
        {
            System.out.println("error with file");
        }
        itemDao.save(item);
    }

    public void delete(int id)
    {
        itemDao.delete(id);
    }

    public void update(Item item, MultipartFile multipartFile)
    {
        String path = "E:" + File.separator + "Downloads" + File.separator + "apache-tomcat-8.0.43" + File.separator + "resources" + File.separator +
                item.getName() + File.separator + multipartFile.getOriginalFilename();

        item.setPathImage("resources" + File.separator + item.getName() + File.separator + multipartFile.getOriginalFilename());

        File file = new File(path);

        try
        {
            file.mkdirs();
            try
            {
                FileUtils.cleanDirectory
                        (new File(System.getProperty("catalina.home") + File.separator +"resources" + File.separator
                                + item.getName() + File.separator));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            multipartFile.transferTo(file);
        }
        catch (IOException e)
        {
            System.out.println("error with file");
        }
        itemDao.save(item);
    }

    public Item getOne(int id)
    {
        return itemDao.findOne(id);
    }

    public List<Item> getAll()
    {
        return itemDao.findAll();
    }

    @Override
    public Page<Item> findAllPages(Pageable pageable)
    {
        return itemDao.findAll(pageable);
    }

    @Override
    public void update(Item item)
    {
        itemDao.save(item);
    }
}
