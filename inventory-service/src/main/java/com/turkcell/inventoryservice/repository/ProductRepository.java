package com.turkcell.inventoryservice.repository;

import com.turkcell.inventoryservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
