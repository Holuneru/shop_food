package com.example.shop_food.controler;

import org.springframework.web.bind.annotation.RestController;

import com.example.shop_food.repository.Costumer;
import com.example.shop_food.service.CostumerService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping(path = "/ShopFood")
public class CostumerControler {

    private final CostumerService costumerService;

    public CostumerControler(CostumerService costumerService) {
        this.costumerService = costumerService;
    }

    @GetMapping("/api/costumers")
    public List<Costumer> getAllCostumers() {
        return costumerService.findAllCostumers();
    }

    @PostMapping("/registration")
    public Costumer addCostumer(@RequestBody Costumer costumer ) {

        
        return costumerService.addCostumer(costumer);
    }

    @GetMapping("/login")
    public Costumer loginCostumer(@RequestBody Costumer costumer) {
        
        
        return costumerService.loginCostumer(costumer.getName(), costumer.getPassword());
    }
    
    @DeleteMapping("/api/del/{id}")
    public void deleteCostumer(@PathVariable Long id){
        costumerService.deleteCostumer(id);
    }
    
    @PutMapping("/updateBalance")
    public void updateBalance(@RequestParam Long id, @RequestParam Integer balance) {
       costumerService.updateBalance(id, balance);
    }
    

    
}
