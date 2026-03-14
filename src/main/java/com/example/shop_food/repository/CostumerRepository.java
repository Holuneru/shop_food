package com.example.shop_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {

    Optional<Costumer> findById(Long id);
    Costumer findByName(String name);
    List<Costumer> findByBalance(Integer balance);

    
    
}
