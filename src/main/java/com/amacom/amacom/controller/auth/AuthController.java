package com.amacom.amacom.controller.auth;

import com.amacom.amacom.dto.auth.AuthResponseDTO;
import com.amacom.amacom.mapper.auth.AuthResponseMapper;
import com.amacom.amacom.mapper.auth.LoginRequestMapper;
import com.amacom.amacom.mapper.auth.RegisterRequestMapper;
import com.amacom.amacom.model.auth.AuthResponse;
import com.amacom.amacom.dto.auth.LoginRequestDTO;
import com.amacom.amacom.dto.auth.RegisterRequestDTO;
import com.amacom.amacom.service.interfaces.auth.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final IAuthService IAuthService;
    
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        AuthResponse response = this.IAuthService.login(LoginRequestMapper.INSTANCE.toLoginRequest(loginRequestDTO));

        if (response == null) {
            return new ResponseEntity<>(new AuthResponseDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AuthResponseMapper.INSTANCE.toAuthResponseDTO(response), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO)
    {
        AuthResponse response = this.IAuthService.register(RegisterRequestMapper.INSTANCE.toRegisterRequest(registerRequestDTO));

        if (response == null) {
            return new ResponseEntity<>(new AuthResponseDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AuthResponseMapper.INSTANCE.toAuthResponseDTO(response), HttpStatus.OK);
    }
}
