package com.turkcell.paymentservice.service.impl;

import com.turkcell.commonservice.config.exception.EntityNotFoundException;
import com.turkcell.commonservice.dto.request.CreatePaymentRequest;
import com.turkcell.commonservice.dto.response.ClientResponse;
import com.turkcell.commonservice.util.constant.CommonConstant;
import com.turkcell.paymentservice.dto.requset.PaymentRequest;
import com.turkcell.paymentservice.dto.response.PaymentResponse;
import com.turkcell.paymentservice.exception.InvalidPaymentException;
import com.turkcell.paymentservice.exception.PaymentBalanceException;
import com.turkcell.paymentservice.model.Payment;
import com.turkcell.paymentservice.repository.PaymentRepository;
import com.turkcell.paymentservice.service.PaymentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PaymentResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<PaymentResponse> response = payments.stream().map(this::createPaymentResponse).toList();

        return response;
    }

    @Override
    public PaymentResponse getById(UUID id) {
        Payment payment = getPaymentById(id);
        PaymentResponse response = createPaymentResponse(payment);

        return response;
    }

    @Override
    public PaymentResponse create(PaymentRequest request) {
        Payment payment = new Payment();
        fillPayment(request,payment);

        PaymentResponse response = createPaymentResponse(repository.save(payment));

        return response;
    }

    @Override
    public PaymentResponse updateById(UUID id, PaymentRequest request) {
        Payment payment = getPaymentById(id);
        fillPayment(request,payment);

        PaymentResponse response = createPaymentResponse(repository.save(payment));

        return response;
    }

    @Override
    public void deleteById(UUID id) {
        repository.delete(getPaymentById(id));
    }

    @Override
    public ClientResponse paymentControl(CreatePaymentRequest request) {
        Payment payment = checkIfPaymentValid(request);
        checkIfPaymentBalance(payment, request.price());

        payment.setBalance(payment.getBalance().subtract(request.price()));

        ClientResponse<String> response = new ClientResponse<>(true,"Payment is successfull!");

        return response;
    }

    private Payment getPaymentById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CommonConstant.Exception.PAYMENT_NOT_FOUND));
    }

    private PaymentResponse createPaymentResponse(Payment payment){
        return new PaymentResponse(
                payment.getId(),
                payment.getCardNumber(),
                payment.getCardHolder(),
                payment.getCardExpirationYear(),
                payment.getCardExpirationMonth(),
                payment.getCardCvv(),
                payment.getBalance());
    }

    private void fillPayment(PaymentRequest request, Payment payment){
        payment.setCardHolder(request.cardHolder());
        payment.setCardNumber(request.cardNumber());
        payment.setCardExpirationYear(request.cardExpirationYear());
        payment.setCardExpirationMonth(request.cardExpirationMonth());
        payment.setCardCvv(request.cardCvv());
        payment.setBalance(request.balance());
    }

    private Payment checkIfPaymentValid(CreatePaymentRequest request){
      Payment payment = repository.findByCardNumberAndCardHolderAndCardCvvAndCardExpirationMonthAndCardExpirationYear(
                request.cardNumber(),
                request.cardHolder(),
                request.cardCvv(),
                request.cardExpirationMonth(),
                request.cardExpirationYear())
              .orElseThrow(() -> new InvalidPaymentException(CommonConstant.Exception.INVALID_PAYMENT_EXCEPTION));

      return payment;
    }

    private void checkIfPaymentBalance(Payment payment, BigDecimal price){
        if (payment.getBalance().compareTo(price) < 0){
            throw new PaymentBalanceException(CommonConstant.Exception.BALANCE_IS_INSUFFICIENT);
        }
    }
}
