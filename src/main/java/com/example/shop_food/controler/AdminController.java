package com.example.shop_food.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_food.AdminService.CostumerAdminFunc;
import com.example.shop_food.repository.Costumer;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CostumerAdminFunc costumerAdminFunc;

    @GetMapping("/infoCostumers")
    public List<Costumer> infoCostumers() {
        return costumerAdminFunc.getAllInfoCostumers();
    }
    
    
}
