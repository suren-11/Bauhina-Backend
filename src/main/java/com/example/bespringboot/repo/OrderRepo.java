package com.example.bespringboot.repo;

import com.example.bespringboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface OrderRepo extends JpaRepository<Order,Long> {
    Order findTopByOrderByIdDesc();
    /** for order report view */

   List<Order> findByDate(String date);
   
    /**for customer order tracking */
   List<Order> findByCustomerId(String id);
}
