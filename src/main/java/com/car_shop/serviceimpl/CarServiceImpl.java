package com.car_shop.serviceimpl;

import com.car_shop.dao.CarDao;
import com.car_shop.entity.Car;
import com.car_shop.service.CarService;
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
public class CarServiceImpl implements CarService
{
    private final CarDao carDao;

    private final Validator validator;

    @Autowired
    public CarServiceImpl(CarDao carDao, @Qualifier("itemValidator") Validator validator)
    {
        this.carDao = carDao;
        this.validator = validator;
    }

    public void add(Car car, MultipartFile multipartFile) throws Exception
    {
        validator.validate(car);
        String path = "E:" + File.separator + "Downloads" + File.separator + "apache-tomcat-8.0.43" + File.separator + "resources" + File.separator +
                car.getName() + File.separator + multipartFile.getOriginalFilename();

        car.setPathImage("resources" + File.separator + car.getName() + File.separator + multipartFile.getOriginalFilename());

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
        carDao.save(car);
    }

    public void delete(int id)
    {
        carDao.delete(id);
    }

    public void update(Car car, MultipartFile multipartFile)
    {
        String path = "E:" + File.separator + "Downloads" + File.separator + "apache-tomcat-8.0.43" + File.separator + "resources" + File.separator +
                car.getName() + File.separator + multipartFile.getOriginalFilename();

        car.setPathImage("resources" + File.separator + car.getName() + File.separator + multipartFile.getOriginalFilename());

        File file = new File(path);

        try
        {
            file.mkdirs();
            try
            {
                FileUtils.cleanDirectory
                        (new File(System.getProperty("catalina.home") + File.separator +"resources" + File.separator
                                + car.getName() + File.separator));
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
        carDao.save(car);
    }

    public Car getOne(int id)
    {
        return carDao.findOne(id);
    }

    public List<Car> getAll()
    {
        return carDao.findAll();
    }

    @Override
    public Page<Car> findAllPages(Pageable pageable)
    {
        return carDao.findAll(pageable);
    }

    @Override
    public void update(Car car)
    {
        carDao.save(car);
    }
}
