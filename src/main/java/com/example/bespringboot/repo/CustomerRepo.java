package com.example.bespringboot.repo;

import com.example.bespringboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    /**searching a customer by email */
    Optional<Customer> findByEmail(String email);

    }
