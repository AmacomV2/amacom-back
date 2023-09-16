package com.amacom.amacom.service.interfaces.auth;

import com.amacom.amacom.model.auth.AuthResponse;
import com.amacom.amacom.model.auth.LoginRequest;
import com.amacom.amacom.model.auth.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);
}
