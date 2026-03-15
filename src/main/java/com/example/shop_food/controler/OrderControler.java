package com.example.shop_food.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_food.repository.Order;
import com.example.shop_food.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(path = "/ShopFood")
public class OrderControler {

    private final OrderService orderService;

    public OrderControler(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("api/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    

    @PostMapping("/buy")
    public void buyFood(@RequestParam Long costumerId, @RequestParam Long foodId ) {

        orderService.buyFood(costumerId, foodId);
        
        
    }
    




    
}
