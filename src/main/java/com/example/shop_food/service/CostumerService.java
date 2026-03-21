package com.example.shop_food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_food.DTO.CostumerShortDTO;
import com.example.shop_food.repository.Costumer;
import com.example.shop_food.repository.CostumerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CostumerService {


    
    private final CostumerRepository costumerRepository;


    public CostumerShortDTO getCostumerDTO_test(Long id){
        Costumer costumer = costumerRepository.findById(id).orElseThrow(()-> new RuntimeException("Costumer undefound"));
        CostumerShortDTO dto = new CostumerShortDTO(costumer.getName(), costumer.getBalance(), costumer.getOrders_counter());
        return dto;
    }
    


    public List<Costumer> findAllCostumers(){
        return costumerRepository.findAll();
    }


    public CostumerShortDTO addCostumer(Costumer costumer){

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
        
        // costumer.setBalance(0);
        // costumer.setOrders_counter(0);
        CostumerShortDTO dto = new CostumerShortDTO(costumer.getName(),costumer.getBalance(),costumer.getOrders_counter());
        return dto;
        
    }

    public CostumerShortDTO deleteCostumer(Long id){

        Optional<Costumer> costumerOptional = costumerRepository.findById(id);

        if (costumerOptional.isEmpty()) {

            throw new RuntimeException("ID undefound");
            
        }

        Costumer costumer = costumerRepository.findById(id).orElseThrow();
        CostumerShortDTO dto = new CostumerShortDTO(costumer.getName(), costumer.getBalance(), costumer.getOrders_counter());


        costumerRepository.deleteById(id);

        return dto;
    }

    public CostumerShortDTO  loginCostumer(String name, String password){
        Costumer costumer = costumerRepository.findByName(name);


        if (costumer == null) {

            throw new RuntimeException("User is undefound");
            
        }

        if (!costumer.getPassword().equals(password)) {

            throw new RuntimeException("Wrong Password");
            
        }

        CostumerShortDTO dto = new CostumerShortDTO(costumer.getName(), costumer.getBalance(), costumer.getOrders_counter());


        return dto;

    }
    @Transactional
    public CostumerShortDTO updateBalance(Long id, Integer balance){

        if (balance <0|| balance == null) {
            throw new RuntimeException("Invalide balanceResponse");
        }
        Costumer costumer = costumerRepository.findById(id).orElseThrow(()-> new RuntimeException());
        if (costumer.getBalance() == null || costumer.getBalance()<0) {
            costumer.setBalance(0);
        }
        costumer.setBalance(costumer.getBalance()+ balance);

        CostumerShortDTO dto = new CostumerShortDTO(costumer.getName(), costumer.getBalance(), costumer.getOrders_counter());
        return dto;
        


    }

    @Transactional
    public CostumerShortDTO updateCostumer(Long id, String name,String password ){

        Costumer costumer = costumerRepository.findById(id).orElseThrow(()-> new RuntimeException("User whith id: "+ id+" undefound"));
        if (name != null && !name.equals(costumer.getName()) && !name.isBlank()) {
            costumer.setName(name);
            log.info("Name was set: "+name);
        }
        
        if (password!= null && !password.equals(costumer.getPassword()) && !password.isBlank()) {
            if (password.length()<8) {
                throw new RuntimeException("Password length < 8");
            }
            costumer.setPassword(password);
            log.info("Password was set: "+password);
            
        }

        CostumerShortDTO dto = new CostumerShortDTO(costumer.getName(), costumer.getBalance(), costumer.getOrders_counter());
        return dto;
        
        
        

    }



    
}
