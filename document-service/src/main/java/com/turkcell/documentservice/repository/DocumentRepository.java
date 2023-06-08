package com.turkcell.documentservice.repository;

import com.turkcell.documentservice.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
    List<Document> findAllByProductId(UUID productId);
}
