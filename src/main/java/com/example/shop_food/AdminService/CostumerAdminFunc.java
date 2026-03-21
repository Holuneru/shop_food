package com.example.shop_food.AdminService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_food.repository.Costumer;
import com.example.shop_food.repository.CostumerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CostumerAdminFunc {
    private final CostumerRepository costumerRepository;

    public List<Costumer> getAllInfoCostumers(){
        return costumerRepository.findAll();
    }
   
}
