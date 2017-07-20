package com.car_shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="billable")
public class Billable extends AbstractEntity
{
    @Column(name="billableTime")
    private LocalDateTime localDateTime;

    private int price;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "item_billable",
            joinColumns = @JoinColumn(name = "billableId"),
            inverseJoinColumns = @JoinColumn(name = "itemId"))
    private Set<Item> item = new HashSet<>();

    public Billable() {}

    public Billable(LocalDateTime localDateTime)
    {
        super();
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString()
    {
        return "Time: " + localDateTime + ", User: " + user + ", Item" + item;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }
}