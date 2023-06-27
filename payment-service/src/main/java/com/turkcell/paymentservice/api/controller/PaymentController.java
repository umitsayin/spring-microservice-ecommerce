package com.turkcell.paymentservice.api.controller;

import com.turkcell.commonservice.dto.request.CreatePaymentRequest;
import com.turkcell.commonservice.dto.response.ClientResponse;
import com.turkcell.paymentservice.dto.requset.PaymentRequest;
import com.turkcell.paymentservice.dto.response.PaymentResponse;
import com.turkcell.paymentservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAll(){
        return ResponseEntity.ok(paymentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@RequestBody PaymentRequest request){
        return ResponseEntity.ok(paymentService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updateById(@PathVariable UUID id, @RequestBody PaymentRequest request){
        return ResponseEntity.ok(paymentService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){
        paymentService.deleteById(id);
    }

    @PostMapping("/check-payment")
    public ResponseEntity<ClientResponse> paymentControl(@RequestBody CreatePaymentRequest request){
        return ResponseEntity.ok(paymentService.paymentControl(request));
    }
}
