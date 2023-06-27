package com.turkcell.saleservice.model;

import jakarta.persistence.Entity;

@Entity
public class SaleItem extends BaseEntity{
    private String productId;

    public SaleItem() {
    }

    public SaleItem(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
