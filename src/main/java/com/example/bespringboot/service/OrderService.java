package com.example.bespringboot.service;

// import com.example.bespringboot.entity.CartItem;
import com.example.bespringboot.entity.Order;

// import com.example.bespringboot.entity.Staff;
// import com.example.bespringboot.repo.CartItemRepo;
import com.example.bespringboot.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;

    public Order save(Order order){ return orderRepo.save(order);}
    public Order findTopByOrderByIdDesc(){return orderRepo.findTopByOrderByIdDesc();}

    /**getting all orders form database */
    public List<Order> findAll(){return orderRepo.findAll();}

    public List<Order> findByDate(String date){return orderRepo.findByDate(date);}

    public List<Order> findByCustomerId(String id){return orderRepo.findByCustomerId(id);}
}
