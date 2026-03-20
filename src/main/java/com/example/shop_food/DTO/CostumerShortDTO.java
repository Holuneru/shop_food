package com.example.shop_food.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostumerShortDTO {
    private String name;
    private Integer balance;
    private Integer orders_counter;
}
