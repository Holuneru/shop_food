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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CostumerRepository costumerRepository;
    private final FoodRepository foodRepository;
    

    
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderByID(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Id is Empty"));
        return order;
    }

    public List<Order> getAllCodtumerOrders(Long costumerId){
        List<Order> orders = orderRepository.findByCostumerId(costumerId);
        if (orders.isEmpty()) {

            throw new RuntimeException("No Order with costumerID: "+costumerId);
        }
        return orders;
    }


    @Transactional
    public void buyFood(Long costumerId, Long foodId){

        Costumer costumer = costumerRepository.findById(costumerId).orElseThrow(()-> new RuntimeException("Costumer NOT found"));
        Food food = foodRepository.findById(foodId).orElseThrow(()-> new RuntimeException("Food NOT found"));

        if (costumer.getBalance()<food.getPrice()) {
            throw new RuntimeException("Not enough money");
        }

        costumer.setBalance(costumer.getBalance()-food.getPrice());
        if (costumer.getOrders_counter()==null) {
            costumer.setOrders_counter(0);
        }
        costumer.setOrders_counter(costumer.getOrders_counter()+1);
        Order order = new Order();

        order.setCostumerId(costumerId);
        order.setFoodId(foodId);
        order.setPrice(food.getPrice());
        foodRepository.deleteById(foodId);
        orderRepository.save(order);

    }

    
}
