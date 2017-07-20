package com.car_shop.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="item")
public class Item extends AbstractEntity
{
    @Column(name="itemName")
    private String name;

    @Column(name="itemContent")
    private String content;

    @Column(name="itemPrice")
    private int price;

    private int quantity;

    @Column(name = "image")
    private String pathImage;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(name = "user_item",
            joinColumns = @JoinColumn(name = "itemId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "item_billable",
            joinColumns = @JoinColumn(name = "itemId"),
            inverseJoinColumns = @JoinColumn(name = "billableId"))

    private Set<Billable> billable = new HashSet<>();

    public Item() {}

    public Item(String name, String content, int price, Category category) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.quantity = 1;
        this.category = category;
    }

    public Item(String name, String content, int price, String pathImage, Category category) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.pathImage = pathImage;
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + ", Content: " + content + ", Price: " + price
                + ", Category: " + category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Billable> getBillable() {
        return billable;
    }

    public void setBillable(Set<Billable> billable) {
        this.billable = billable;
    }
}