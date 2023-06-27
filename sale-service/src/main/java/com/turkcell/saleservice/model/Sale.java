package com.turkcell.saleservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale extends BaseEntity{
    private String description;
    private BigDecimal totalPrice;

    @OneToMany
    private List<SaleItem> saleItems = new ArrayList<>();

    public Sale(){

    }
    
    public Sale(String description, BigDecimal totalPrice, List<SaleItem> saleItems) {
        this.description = description;
        this.totalPrice = totalPrice;
        this.saleItems = saleItems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }
}
