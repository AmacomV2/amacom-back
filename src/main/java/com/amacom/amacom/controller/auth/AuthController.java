package com.amacom.amacom.controller.auth;

import com.amacom.amacom.dto.auth.AuthResponseDTO;
import com.amacom.amacom.mapper.auth.AuthResponseMapper;
import com.amacom.amacom.mapper.auth.LoginRequestMapper;
import com.amacom.amacom.mapper.auth.RegisterRequestMapper;
import com.amacom.amacom.model.auth.AuthResponse;
import com.amacom.amacom.dto.auth.LoginRequestDTO;
import com.amacom.amacom.dto.auth.RegisterRequestDTO;
import com.amacom.amacom.model.auth.Usuario;
import com.amacom.amacom.repository.auth.IUsuarioRepository;
import com.amacom.amacom.service.interfaces.IUsuarioService;
import com.amacom.amacom.service.interfaces.auth.IAuthService;
import com.amacom.amacom.service.interfaces.auth.IPasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final IAuthService IAuthService;

    private IPasswordResetService passwordResetService;

    private IUsuarioService usuarioService;

    private IUsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;
    
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

    @PostMapping("/enviarCodigo")
    public ResponseEntity<String> resetPassword(@RequestParam(name = "email") String email) {
        Usuario user = usuarioService.findByEmail(email);
        if (user != null) {
            passwordResetService.sendPasswordResetCode(email);
            return ResponseEntity.ok("Código de recuperación enviado por correo electrónico.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Correo electrónico no encontrado.");
        }
    }

    @PostMapping("/cambiarPassword")
    public ResponseEntity<String> changePassword(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "codigo") String codigo,
            @RequestParam(name = "password") String password
    ) {
        Usuario user = usuarioService.findByEmail(email);
        var token = passwordResetService.isCodeValid(email, codigo);
        if (Boolean.TRUE.equals(token)) {
            if (user != null) {
                user.setPassword(passwordEncoder.encode(password));
                usuarioRepository.save(user);
                return ResponseEntity.ok("Contraseña actualizada correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El email proporcionado no se encuentra registrado en la base de datos.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Código de recuperación no válido o ha expirado.");
        }
    }

    @Autowired
    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setUsuarioRepository(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setPasswordResetService(IPasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }
}
