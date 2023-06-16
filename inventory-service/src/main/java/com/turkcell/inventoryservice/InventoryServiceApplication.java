package com.turkcell.inventoryservice;

import com.turkcell.commonservice.util.constant.CommonConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {CommonConstant.COMMON_SERVICE_BASE_PACKAGE,
		CommonConstant.INVENTORY_SERVICE_BASE_PACKAGE})
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
