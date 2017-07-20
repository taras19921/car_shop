package com.car_shop.dao;

import com.car_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryDao extends JpaRepository<Category, Integer>
{
    @Query("select c from Category c left join fetch c.item where c.id=:id")
    Category categoryWithItems(@Param("id") int id);
}
