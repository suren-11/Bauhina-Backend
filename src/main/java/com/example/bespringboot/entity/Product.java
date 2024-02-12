package com.example.bespringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long code;
    String name;
    String type;
    int qty;
    double cost;
    double unitPrice;
    @Column(name = "pic_byte",length = Integer.MAX_VALUE)
    byte[] picByte;

}
