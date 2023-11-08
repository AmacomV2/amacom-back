package com.amacom.amacom.service.interfaces.auth;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.auth.AuthResponse;
import com.amacom.amacom.model.auth.LoginRequest;
import com.amacom.amacom.model.auth.RegisterRequest;

@Service
public interface IAuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);

    boolean validateCredentials(LoginRequest request);
}
