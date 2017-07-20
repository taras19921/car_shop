package com.car_shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="category")
public class Category extends AbstractEntity
{
    @Column(name="categoryName")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Item> item = new HashSet<>();

    public Category() {}

    public Category(String name)
    {
        super();
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }
}