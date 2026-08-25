package com.ecomm.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private double price;

    private double discountPrice;
    private int quantity;
    private String brand;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt=Instant.now();
        updatedAt=Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt=Instant.now();
    }
}
