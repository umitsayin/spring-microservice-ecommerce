package com.turkcell.saleservice;

import com.turkcell.commonservice.util.constant.CommonConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {CommonConstant.COMMON_SERVICE_BASE_PACKAGE,
        CommonConstant.SALE_SERVICE_BASE_PACKAGE})public class SaleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleServiceApplication.class, args);
    }

}
