package com.example.identityproject.service;

import com.example.identityproject.dto.Request.AuthenticationRequest;
import com.example.identityproject.exeption.AppException;
import com.example.identityproject.exeption.ErorrCode;
import com.example.identityproject.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public boolean authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErorrCode.USER_NOT_EXIST));

        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
