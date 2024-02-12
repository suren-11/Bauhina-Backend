package com.example.bespringboot.repo;

import com.example.bespringboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
// import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    List<Product> findByType(String type);

}
