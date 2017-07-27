package com.car_shop.controller;

import com.car_shop.editors.BrandEditor;
import com.car_shop.entity.Brand;
import com.car_shop.entity.Car;
import com.car_shop.service.CategoryService;
import com.car_shop.service.CarService;
import com.car_shop.validator.item.ItemValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ItemController
{
    private final CarService carService;

    private final CategoryService categoryService;

    @Autowired
    public ItemController(CarService carService, CategoryService categoryService) {
        this.carService = carService;
        this.categoryService = categoryService;
    }

    @InitBinder
    public void init(WebDataBinder binder)
    {
        binder.registerCustomEditor(Brand.class, new BrandEditor());
    }

    @GetMapping("/item")
    public String addItem(Model model)
    {
        model.addAttribute("item", new Car());
        model.addAttribute("categories", categoryService.getAll());
        return "views-admin-item";
    }

    @PostMapping("/saveItem")
    public String addItem(@ModelAttribute Car car, Model model, @RequestAttribute("image") MultipartFile image)
    {
        try
        {
            carService.add(car, image);
        }
        catch (Exception e)
        {
            if(e.getMessage().equals(ItemValidationMessages.EMPTY_ITEMNAME_FIELD)
                    || e.getMessage().equals(ItemValidationMessages.ITEMNAME_ALREADY_EXIST))
            {
                model.addAttribute("itemnameException", e.getMessage());
            }
            else if(e.getMessage().equals(ItemValidationMessages.EMPTY_CONTENT_FIELD))
            {
                model.addAttribute("itemcontentException", e.getMessage());
            }
            else if(e.getMessage().equals(ItemValidationMessages.ZERO_PRICE_FIELD))
            {
                model.addAttribute("itempriceException", e.getMessage());
            }
            model.addAttribute("categories", categoryService.getAll());
            return "views-admin-item";
        }
        return "redirect:/";
    }

    @GetMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id)
    {
        carService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/updateItem/{id}")
    public String getItem(@PathVariable int id, Model model)
    {
        model.addAttribute("itemAttribute", carService.getOne(id));
        model.addAttribute("categories", categoryService.getAll());
        return "views-admin-updateItem";
    }

    @PostMapping("/updateItem/{id}")
    public  String updateItem(@ModelAttribute Car car, @PathVariable int id,
                              @RequestAttribute("image") MultipartFile image)
    {
        car.setId(id);
        if(image.isEmpty())
        {
            carService.update(car);
        }
        else
        {
            carService.update(car, image);
        }
        return "redirect:/listOfItems";
    }

    @GetMapping("/listOfItems")
    public String allItems(Model model, @PageableDefault Pageable pageable)
    {
        model.addAttribute("cars", carService.findAllPages(pageable));
        return "views-admin-listOfItems";
    }
}
