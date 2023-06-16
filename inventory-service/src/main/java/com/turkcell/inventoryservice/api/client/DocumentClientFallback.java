package com.turkcell.inventoryservice.api.client;

import com.turkcell.commonservice.dto.response.DocumentResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class DocumentClientFallback implements DocumentClient{
    @Override
    public DocumentResponse create(MultipartFile[] files, String productId) {
        throw new RuntimeException("DOCUMENT_SERVICE_DOWN");
    }

    @Override
    public DocumentResponse getDocumentsByProductId(String productId) {
        throw new RuntimeException("DOCUMENT_SERVICE_DOWN");
    }
}
