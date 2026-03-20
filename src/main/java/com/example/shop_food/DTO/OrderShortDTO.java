package com.example.shop_food.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderShortDTO {
    private String nameCostumer;
    private String foodName;
    private Integer price;
}
