package com.turkcell.inventoryservice.api.client;

import com.turkcell.commonservice.dto.response.DocumentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(name = "document-service", fallback = DocumentClientFallback.class)
public interface DocumentClient {

    @PostMapping(value = "/api/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    DocumentResponse create(@RequestPart("files") MultipartFile[] files,
                            @RequestParam("productId") String productId);

    @GetMapping("/api/documents")
    DocumentResponse getDocumentsByProductId(@RequestParam String productId);
}