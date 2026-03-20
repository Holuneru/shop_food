package com.example.shop_food.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodShortDTO {

    private String nameFood;
    private String type;
    private Integer price;
    
}