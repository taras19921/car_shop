package com.car_shop.controller;

import com.car_shop.editors.ItemEditor;
import com.car_shop.editors.UserEditor;
import com.car_shop.entity.Billable;
import com.car_shop.entity.Item;
import com.car_shop.entity.User;
import com.car_shop.service.BillableService;
import com.car_shop.service.ItemService;
import com.car_shop.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@Controller
@Transactional
public class BillableController {
    private final BillableService billableService;

    private final UserService userService;

    private final ItemService itemService;

    @Autowired
    public BillableController(BillableService billableService, UserService userService, ItemService itemService) {
        this.billableService = billableService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserEditor());
        binder.registerCustomEditor(Item.class, new ItemEditor());
    }

    @GetMapping(value = "/billable")
    public String addBillable(Model model) {
        model.addAttribute("billable", new Billable());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("items", itemService.getAll());
        return "views-admin-billable";
    }

    @PostMapping(value = "/saveBillable")
    public String addBillable(@ModelAttribute Billable billable) {
        billableService.add(billable);
        return "redirect:/";
    }

    @GetMapping(value = "/deleteBillable/{id}")
    public String deleteBillable(@PathVariable int id) {
        billableService.delete(id);
        return "redirect:/";
    }

    @GetMapping(value = "/updateBillable/{id}")
    public String getBillable(@PathVariable int id, Model model) {
        model.addAttribute("billableAttribute", billableService.getOne(id));
        Billable billable = billableService.getOne(id);
        Hibernate.initialize(billable.getItem());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("items", itemService.getAll());
        return "views-admin-updateBillable";
    }

    @PostMapping(value = "/updateBillable/{id}")
    public String updateBillable(@ModelAttribute Billable billable, @PathVariable int id, Model model) {
        billable.setId(id);
        billableService.update(billable);
        model.addAttribute("billables", billableService.getAll());
        return "redirect:/listOfBillables";
    }

    @GetMapping("/billableDetails/{id}")
    public String billableDetails(@PathVariable int id, Model model) {
        Billable billable = billableService.getOne(id);
        billableService.getTotalPrice(id);
        model.addAttribute("billable", billable);
        Hibernate.initialize(billable.getItem());
        return "views-user-billableDetails";
    }

    @GetMapping("/listOfBillables")
    public String allBillables(Model model, @PageableDefault Pageable pageable) {
        Set<Billable> billables = billableService.getBillableWithItems();
        for (Billable billable : billables) {
            Hibernate.initialize(billable.getItem());
        }
        model.addAttribute("billables", billableService.findAllPages(pageable));
        return "views-admin-listOfBillables";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(Principal principal, @PathVariable int id) {
        billableService.makeSleep();
        billableService.addToCart(principal, id);
        return "redirect:/";
    }

    @GetMapping("/deleteFromCart/{userId}/{itemId}")
    public String deleteFromCart(@PathVariable int userId, @PathVariable int itemId) {
        billableService.deleteFromCart(userId, itemId);
        return "redirect:/cart";
    }

    @PostMapping("/buy")
    public String buy(Principal principal, @ModelAttribute("itemQuantity") Integer itemQuantity) {
        User user = userService.getOne(Integer.parseInt(principal.getName()));
        for (Item item : user.getItems()) {
            item.setQuantity(itemQuantity);
            itemService.update(item);
        }
        billableService.buy(Integer.parseInt(principal.getName()));
        return "redirect:/profile";
    }
}
