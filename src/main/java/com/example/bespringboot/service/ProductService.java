package com.example.bespringboot.service;

import com.example.bespringboot.entity.Product;
import com.example.bespringboot.repo.ProductRepo;
// import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public Product saveProduct(Product product){return productRepo.save(product);}

    public List<Product> findAll(){return productRepo.findAll();}

    public byte[] getImage(String code){
        return productRepo.findById(code).map(Product::getPicByte).orElse(null);
    }

    public Optional<Product> getProductForUpdate(String id){ return productRepo.findById(id);}

    public List<Product> getByType(String type){return productRepo.findByType(type);}

    public Optional<Product> getByCode(String code){return productRepo.findById(code);}

    /**searching a product from database by code */
    public Optional<Product> findByCode(String code) { return productRepo.findById(code);
    }

    /**delete a product from database */
    public void delete(Product product) { productRepo.delete(product);
    }
}
