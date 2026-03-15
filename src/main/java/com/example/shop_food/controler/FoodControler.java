package com.example.shop_food.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_food.repository.Food;
import com.example.shop_food.service.FoodService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "/ShopFood")
public class FoodControler {

    private final FoodService foodService;
    

    public FoodControler(FoodService foodService) {
        this.foodService = foodService;
    }


    @GetMapping("/getAllListFood")
    public List<Food> getMethodName(@RequestParam String param) {
        return foodService.getAllFoodItems();
    }

    @PostMapping("/createItem")
    public Food createFoodItemFood(@RequestBody Food food) {
        
        
        return foodService.createFoodItem(food);
    }
    
    
    
}
