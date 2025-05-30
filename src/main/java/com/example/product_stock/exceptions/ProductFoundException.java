package com.example.product_stock.exceptions;

public class ProductFoundException extends RuntimeException{
    public ProductFoundException(){
        super("Produto ja existe");
    }
}
