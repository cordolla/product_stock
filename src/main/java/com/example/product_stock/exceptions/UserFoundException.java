package com.example.product_stock.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("Usuario ja existe");
    }
}
