package com.example.bespringboot.service;

import com.example.bespringboot.entity.CartItem;
// import com.example.bespringboot.entity.Order;
import com.example.bespringboot.repo.CartItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    public final CartItemRepo cartItemRepo;
    public CartItem save(CartItem cartItems){return cartItemRepo.save(cartItems);}

    public List<CartItem> getList(){return cartItemRepo.findAll();}

}
