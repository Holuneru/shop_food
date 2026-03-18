package com.example.shop_food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shop_food.repository.Food;
import com.example.shop_food.repository.FoodRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;


    public List<Food> getAllFoodItems(){
        return foodRepository.findAll();
    }

    public void deleteFoodItem(Long Idcode){
        Optional<Food> foodOptional = foodRepository.findById(Idcode);
        if (foodOptional.isEmpty()) {

            throw new RuntimeException("Item is EMPTY");
            
        }
        foodRepository.deleteById(Idcode);
    }

    @Transactional
    public void updateItemParam(Long Idcode, String nameFood, Integer price, String type){

        Food food = foodRepository.findById(Idcode).orElseThrow(()->new RuntimeException());
        if (nameFood != null && !nameFood.equals(food.getNameFood())) {
            Optional<Food> optinalFood = foodRepository.findByNameFood(nameFood);
            if (optinalFood.isPresent()) {
                throw new RuntimeException("Food name already use");
            }
            food.setNameFood(nameFood);
            
        }

        if (price != null && price > 0 && !price.equals(food.getPrice())) {
 
            food.setPrice(price);
            
        }

        if (type != null && !type.equals(food.getType())) {
            String typeString = type.toUpperCase();
            food.setType(typeString);
        }

        

    }


    public Food createFoodItem(Food food){

        

        if (food == null) {

            throw new RuntimeException("food is Null");
            
        }
        if (food.getPrice()<=0) {

            throw new RuntimeException("incorrect food_price");
            
        }

        Optional<Food> optinalFood = foodRepository.findByNameFood(food.getNameFood());
        if (food.getNameFood()==null || food.getNameFood().isBlank() || optinalFood.isPresent()) {
            throw new RuntimeException("food_name is Null or already use");
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
