package com.turkcell.paymentservice;

import com.turkcell.commonservice.util.constant.CommonConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {CommonConstant.COMMON_SERVICE_BASE_PACKAGE,
        CommonConstant.PAYMENT_SERVICE_BASE_PACKAGE})
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

}
