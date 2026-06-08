package com.example.identityproject.dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

        String password;
        String firstname;
        String lastname;
        String email;
}
