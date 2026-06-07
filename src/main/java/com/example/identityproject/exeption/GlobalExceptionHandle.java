package com.example.identityproject.exeption;

import com.example.identityproject.dto.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handResponseResponseEntity(RuntimeException runtimeException) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(1001);
        apiResponse.setMessage(runtimeException.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handResponseResponseEntity(AppException runtimeException) {
        ErorrCode erorrCode = runtimeException.getErorrCode();
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(erorrCode.getCode());
        apiResponse.setMessage(erorrCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handResponse(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErorrCode erorrCode = ErorrCode.valueOf(enumKey);
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(erorrCode.getCode());
        apiResponse.setMessage(erorrCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
