package com.turkcell.invoiceservice;

import com.turkcell.commonservice.util.constant.CommonConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {CommonConstant.COMMON_SERVICE_BASE_PACKAGE,
        CommonConstant.INVOICE_SERVICE_BASE_PACKAGE})
public class InvoiceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceServiceApplication.class, args);
    }

}
