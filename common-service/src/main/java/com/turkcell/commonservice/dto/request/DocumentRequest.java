package com.turkcell.commonservice.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


public class DocumentRequest {
    private UUID productId;
    private List<MultipartFile> documents;

    public DocumentRequest(UUID productId, List<MultipartFile> documents) {
        this.productId = productId;
        this.documents = documents;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public List<MultipartFile> getDocuments() {
        return documents;
    }

    public void setDocuments(List<MultipartFile> documents) {
        this.documents = documents;
    }
}
