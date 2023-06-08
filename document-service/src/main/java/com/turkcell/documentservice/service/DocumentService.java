package com.turkcell.documentservice.service;

import com.turkcell.commonservice.dto.response.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface DocumentService {
    DocumentResponse create(MultipartFile[] files, UUID productId);
    DocumentResponse getDocumentsByProductId(UUID productId);
}
