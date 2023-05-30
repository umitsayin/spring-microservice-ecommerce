package com.turkcell.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Document extends BaseModel{
    private String url;
    private String description;

    @ManyToOne
    private Product product;

    public Document() {
    }

    public Document(String url, String description, Product product) {
        this.url = url;
        this.description = description;
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
