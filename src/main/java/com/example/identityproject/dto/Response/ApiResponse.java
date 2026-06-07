package com.example.identityproject.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ApiResponse <T> {
    int code;
    String message;
    T result;
}
