package com.amacom.amacom.service.interfaces.auth;

import org.springframework.stereotype.Service;

@Service
public interface IPasswordResetService {

    void sendPasswordResetCode(String email);

    boolean isCodeValid(String email, String code);
}
