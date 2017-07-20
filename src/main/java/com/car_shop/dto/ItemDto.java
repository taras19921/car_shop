package com.car_shop.dto;

import com.car_shop.entity.Category;

public class ItemDto
{
    private int id;
    private String name;
    private String content;
    private int price;
    private String pathImage;
    private Category category;

    ItemDto() {
    }

    public ItemDto(int id, String name, String content, int price, String pathImage, Category category) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.price = price;
        this.pathImage = pathImage;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPathImage() {
        return pathImage;
    }

    void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", pathImage='" + pathImage + '\'' +
                ", category=" + category +
                '}';
    }
}
