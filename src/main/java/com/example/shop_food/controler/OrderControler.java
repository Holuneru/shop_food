package com.example.shop_food.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_food.DTO.OrderShortDTO;
import com.example.shop_food.repository.Order;
import com.example.shop_food.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(path = "/ShopFood")
@RequiredArgsConstructor
public class OrderControler {

    private final OrderService orderService;

    @GetMapping("api/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("api/order/{orderId}")
    public OrderShortDTO getOrderByID(@PathVariable Long orderId) {
        return orderService.getOrderByID(orderId);
    }

    @GetMapping("api/costumer/order/{costumerId}")
    public List<Order> getAllCodtumerOrders(@PathVariable Long costumerId) {
        return orderService.getAllCodtumerOrders(costumerId);
    }
    
    
    

    @PostMapping("/buy")
    public void buyFood(@RequestParam Long costumerId, @RequestParam Long foodId ) {

        orderService.buyFood(costumerId, foodId);
        
        
    }
    




    
}
