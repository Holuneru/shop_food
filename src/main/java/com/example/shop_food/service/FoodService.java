package com.example.shop_food.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_food.repository.Food;
import com.example.shop_food.repository.FoodRepository;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllFoodItems(){
        return foodRepository.findAll();
    }

    public Food createFoodItem(Food food){

        

        if (food == null) {

            throw new RuntimeException("food is Null");
            
        }
        if (food.getPrice()<=0) {

            throw new RuntimeException("incorrect food_price");
            
        }
        if (food.getNameFood()==null || food.getNameFood().isBlank()) {
            throw new RuntimeException("food_name is Null");
        }
        if (food.getType() == null) {

            throw new RuntimeException("food_type is Null");
            
        }
        String type = food.getType().toUpperCase();
        if (!type.equals("DRINK") && !type.equals("FOOD")) {
            throw new RuntimeException("incorrect food_type"); //Для админа который создает предмет
        }

        food.setType(type);

        return foodRepository.save(food);
    }
    
}
