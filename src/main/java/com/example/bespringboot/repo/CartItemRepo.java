package com.example.bespringboot.repo;

import com.example.bespringboot.entity.CartItem;
// import com.example.bespringboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {

}
