package com.example.bespringboot.service;

import com.example.bespringboot.entity.Customer;
import com.example.bespringboot.repo.CustomerRepo;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    /** initialize the repository method to customer service */
    private final CustomerRepo customerRepo;

    /** pass the customer value to the repository.
     * that repository already extends by JPA Repository
     * so the repository have the inbuilt method save
     * it will save the data to the database*/
    public Customer postCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    public List<Customer> findAll(){
        return customerRepo.findAll();
    }

    public Optional<Customer> findByEmail(String email) {return customerRepo.findByEmail(email);};

    public Optional<Customer> findById(Long id) { return customerRepo.findById(id);}

    public void delete(Customer customer){ customerRepo.delete(customer); }
}
