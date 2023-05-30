package com.turkcell.inventoryservice.repository;

import com.turkcell.inventoryservice.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
}
