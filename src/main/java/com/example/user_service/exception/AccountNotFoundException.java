package com.example.user_service.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message){
        super(message);
    }
}
