package com.turkcell.commonservice.util.constant;

public class CommonConstant {
    public static final String COMMON_SERVICE_BASE_PACKAGE = "com.turkcell.commonservice.config";
    public static final String INVENTORY_SERVICE_BASE_PACKAGE = "com.turkcell.inventoryservice";
    public static final String SALE_SERVICE_BASE_PACKAGE = "com.turkcell.saleservice";
    public static final String PAYMENT_SERVICE_BASE_PACKAGE = "com.turkcell.paymentservice";
    public static final String INVOICE_SERVICE_BASE_PACKAGE = "com.turkcell.invoiceservice";


    public static class Kafka{
        public static final String PRODUCT_DELETED_TOPIC = "product-deleted";
        public static final String PRODUCT_DELETED_GROUP_ID = "inventory-product-delete";
        public static final String SALE_CREATED_TOPIC = "sale-created";
        public static final String SALE_CREATED_GROUP_ID = "sale-service-sale-created";
    }

    public static class Exception{
        public static final String CATEGORY_ALREADY_EXISTS = "Category already exits!";
        public static final String CATEGORY_NOT_FOUND = "Category not found!";
        public static final String PRODUCT_NOT_FOUND = "Product not found!";
        public static final String SALES_NOT_FOUND = "Sale not found!";
        public static final String PAYMENT_NOT_FOUND = "Payment not found!";
        public static final String INVALID_PAYMENT_EXCEPTION = "Invalid payment information!";
        public static final String BALANCE_IS_INSUFFICIENT = "BALANCE IS INSUFFICIENT";
        public static final String INVOICE_NOT_FOUND = "Invoice not found!";
        public static final String PAYMENT_FAILED = "Payment failed!";
    }
}
