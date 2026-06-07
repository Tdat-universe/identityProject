package com.example.identityproject.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "VALID_USERNAME")
    String username;
    @Size(min = 8, message = "VALID_PASSWORD")
    @NotBlank
    String password;
    String firstname;
    String lastname;
    String email;
}
