package com.turkcell.inventoryservice.api.controller;

import com.turkcell.inventoryservice.dto.request.CategoryRequest;
import com.turkcell.inventoryservice.dto.response.CategoryResponse;
import com.turkcell.inventoryservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request){
        return ResponseEntity.ok(categoryService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID id, @RequestBody CategoryRequest request){
        return ResponseEntity.ok(categoryService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){
        categoryService.deleteById(id);
    }
}
