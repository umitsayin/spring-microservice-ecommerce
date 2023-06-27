package com.turkcell.paymentservice.repository;

import com.turkcell.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByCardNumberAndCardHolderAndCardCvvAndCardExpirationMonthAndCardExpirationYear(String cardNumber,
                                                                                                  String cardHolder,
                                                                                                  String cardCvv,
                                                                                                  int cardExpirationMonth,
                                                                                                  int cardExpirationYear);
}
