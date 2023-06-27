package com.turkcell.saleservice.api.controller;

import com.turkcell.saleservice.dto.request.SaleRequest;
import com.turkcell.saleservice.dto.response.SaleResponse;
import com.turkcell.saleservice.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAll(){
        return ResponseEntity.ok(saleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok(saleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SaleResponse> create(@RequestBody SaleRequest request){
        return ResponseEntity.ok(saleService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> updateById(@PathVariable UUID id, @RequestBody SaleRequest request){
        return ResponseEntity.ok(saleService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){
        saleService.deleteById(id);
    }
}
