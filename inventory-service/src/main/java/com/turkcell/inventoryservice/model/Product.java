package com.turkcell.inventoryservice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;

    public Product(){

    }

    public Product(String name, String description, int quantity, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();
}
