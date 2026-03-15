package com.example.shop_food.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_food.repository.Costumer;
import com.example.shop_food.repository.CostumerRepository;
import com.example.shop_food.repository.Food;
import com.example.shop_food.repository.FoodRepository;
import com.example.shop_food.repository.Order;
import com.example.shop_food.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CostumerRepository costumerRepository;
    private final FoodRepository foodRepository;
    

    public OrderService(OrderRepository orderRepository, CostumerRepository costumerRepository,
            FoodRepository foodRepository) {
        this.orderRepository = orderRepository;
        this.costumerRepository = costumerRepository;
        this.foodRepository = foodRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @Transactional
    public void buyFood(Long costumerId, Long foodId){

        Costumer costumer = costumerRepository.findById(costumerId).orElseThrow(()-> new RuntimeException("Costumer NOT found"));
        Food food = foodRepository.findById(foodId).orElseThrow(()-> new RuntimeException("Food NOT found"));

        if (costumer.getBalance()<food.getPrice()) {
            throw new RuntimeException("Not enough money");
        }

        costumer.setBalance(costumer.getBalance()-food.getPrice());

        Order order = new Order();

        order.setCostumerId(costumerId);
        order.setFoodId(foodId);
        order.setPrice(food.getPrice());

        orderRepository.save(order);

    }

    
}
