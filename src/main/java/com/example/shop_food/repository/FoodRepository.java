package com.example.shop_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{

    List<Food> findByType(String type);
    List<Food> findByNameFood(String nameFood);

    
}
