package com.example.identityproject.exeption;

public enum ErorrCode {
    USER_EXIST(1004,"User is exists"),
    VALID_USERNAME(1001,"Username greater 3 character"),
    VALID_PASSWORD(1001,"Password greater 8 character"),
    USER_NOT_EXIST(1005,"User not exists");

    private int code;
    private String message;

    ErorrCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
