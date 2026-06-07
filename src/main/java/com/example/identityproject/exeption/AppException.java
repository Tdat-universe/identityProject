package com.example.identityproject.exeption;

public class AppException extends RuntimeException{
    private ErorrCode erorrCode;

    public AppException(ErorrCode erorrCode) {
        this.erorrCode = erorrCode;
    }

    public ErorrCode getErorrCode() {
        return erorrCode;
    }

    public AppException(String message, ErorrCode erorrCode) {
        super(message);
        this.erorrCode = erorrCode;
    }
}

