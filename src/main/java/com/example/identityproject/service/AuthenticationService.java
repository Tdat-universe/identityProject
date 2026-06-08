package com.example.identityproject.service;

import com.example.identityproject.dto.Request.AuthenticationRequest;
import com.example.identityproject.dto.Request.IntrospectRequest;
import com.example.identityproject.dto.Response.AuthenticationResponse;
import com.example.identityproject.dto.Response.IntrospectResponse;
import com.example.identityproject.exeption.AppException;
import com.example.identityproject.exeption.ErorrCode;
import com.example.identityproject.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @NonFinal
    protected static final String SIGNER_KEY = "e6aee2f7d897a68a2fc796911d0c24cb3bc6ddb0a5f42da87a1ccd9931c8bde6e6aee2f7d897a68a2fc796911d0c24cb3bc6ddb0a5f42da87a1ccd9931c8bde6";
    public IntrospectResponse introspectResponse(IntrospectRequest request) {
        try {
            String token = request.getToken();

            JWSVerifier verifier =
                    new MACVerifier(SIGNER_KEY.getBytes());

            SignedJWT signedJWT =
                    SignedJWT.parse(token);

            Date expiryTime =
                    signedJWT.getJWTClaimsSet()
                            .getExpirationTime();

            boolean verified =
                    signedJWT.verify(verifier);

            return IntrospectResponse.builder()
                    .valid(verified && expiryTime.after(new Date()))
                    .build();

        } catch (Exception e) {
            return IntrospectResponse.builder()
                    .valid(false)
                    .build();
        }
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErorrCode.USER_NOT_EXIST));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErorrCode.UNAUTHENTICATE);
        }
        var token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(String username) {
        try {
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issuer("cny.com")
                    .issueTime(new Date())
                    .expirationTime(
                            new Date(
                                    Instant.now()
                                            .plus(1, ChronoUnit.HOURS)
                                            .toEpochMilli()))
                    .claim("customClaim", "Custom")
                    .build();

            JWSObject jwsObject = new JWSObject(
                    header,
                    new Payload(claimsSet.toJSONObject()));

            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return jwsObject.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException("Cannot generate token", e);
        }
    }
}
