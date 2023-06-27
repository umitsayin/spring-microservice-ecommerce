package com.turkcell.invoiceservice.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Invoice extends BaseEntity{
    private String saleId;
    private BigDecimal totalPrice;

    public Invoice(){

    }

    public Invoice(String saleId, BigDecimal totalPrice) {
        this.saleId = saleId;
        this.totalPrice = totalPrice;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
