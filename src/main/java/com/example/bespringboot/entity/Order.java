package com.example.bespringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "order",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    List<CartItem> cartItems;
    String customerId;
    String date;
    Double total;
    String deliveryAddress;
    boolean status;
}
