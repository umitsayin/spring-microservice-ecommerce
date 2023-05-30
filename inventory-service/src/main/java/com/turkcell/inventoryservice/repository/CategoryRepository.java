package com.turkcell.inventoryservice.repository;

import com.turkcell.inventoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
