package com.turkcell.inventoryservice.api.controller;

import com.turkcell.inventoryservice.dto.request.ProductRequest;
import com.turkcell.inventoryservice.dto.response.ProductResponse;
import com.turkcell.inventoryservice.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductResponse> create(@RequestPart("files") MultipartFile[] files,
                       @RequestPart("productRequest") ProductRequest productRequest){
        return ResponseEntity.ok(service.create(files, productRequest));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateById(@PathVariable UUID id, @RequestBody ProductRequest request){
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        service.deleteById(id);
    }
}
