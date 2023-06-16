package com.turkcell.commonservice.util.constant;

public class CommonConstant {
    public static final String COMMON_SERVICE_BASE_PACKAGE = "com.turkcell.commonservice.config";
    public static final String INVENTORY_SERVICE_BASE_PACKAGE = "com.turkcell.inventoryservice";

    public static class Kafka{
        public static final String PRODUCT_DELETED_TOPIC = "product-deleted";
        public static final String PRODUCT_DELETED_GROUP_ID = "inventory-product-delete";
    }
}
