package com.turkcell.inventoryservice.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductRequest {
    private List<UUID> categoryId;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;

    public ProductRequest(){

    }

    public ProductRequest( List<UUID> categoryId, String name, String description, int quantity, BigDecimal price) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public  List<UUID> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId( List<UUID> categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
