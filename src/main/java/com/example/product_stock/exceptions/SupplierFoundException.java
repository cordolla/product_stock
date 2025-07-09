package com.example.product_stock.exceptions;

public class SupplierFoundException extends RuntimeException {
    public SupplierFoundException() {
        super("There is already a supplier");
    }
}
