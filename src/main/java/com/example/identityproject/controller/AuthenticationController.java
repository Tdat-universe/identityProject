package com.example.identityproject.controller;

import com.example.identityproject.dto.Request.AuthenticationRequest;
import com.example.identityproject.dto.Request.IntrospectRequest;
import com.example.identityproject.dto.Response.ApiResponse;
import com.example.identityproject.dto.Response.AuthenticationResponse;
import com.example.identityproject.dto.Response.IntrospectResponse;
import com.example.identityproject.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }
    @PostMapping("/introsepect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)throws ParseException, JOSEException {
        var result = authenticationService.introspectResponse(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }
}
