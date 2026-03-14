package com.example.shop_food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_food.repository.Costumer;
import com.example.shop_food.repository.CostumerRepository;

@Service
public class CostumerService {


    
    private final CostumerRepository costumerRepository;

    public CostumerService(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }


    public List<Costumer> findAllCostumers(){
        return costumerRepository.findAll();
    }


    public Costumer addCostumer(Costumer costumer){

        if (costumer==null) {

            throw new RuntimeException("Costumer = Null");
            
        }

        if (costumer.getName() == null || costumer.getName().isBlank()) {
            throw new RuntimeException("Name is Empty");
        }

        if (costumer.getPassword() == null) {
            throw new RuntimeException("Pssword is Empty");
        }

        if (costumer.getPassword().length()<8 ) {

            throw new RuntimeException("Password length < 8");
            
        }

        costumer.setBalance(0);
        
        return costumerRepository.save(costumer);
        
    }

    public void deleteCostumer(Long id){

        Optional<Costumer> costumerOptional = costumerRepository.findById(id);

        if (costumerOptional.isEmpty()) {

            throw new RuntimeException("ID undefound");
            
        }


        costumerRepository.deleteById(id);
    }

    public Costumer  loginCostumer(String name, String password){
        Costumer costumer = costumerRepository.findByName(name);


        if (costumer == null) {

            throw new RuntimeException("User is undefound");
            
        }

        if (!costumer.getPassword().equals(password)) {

            throw new RuntimeException("Wrong Password");
            
        }


        return costumer;

    }
    @Transactional
    public void updateBalance(Long id, Integer balance){

        
        Costumer costumer = costumerRepository.findById(id).orElseThrow(()-> new RuntimeException());

        costumer.setBalance(balance);

        


    }



    
}
