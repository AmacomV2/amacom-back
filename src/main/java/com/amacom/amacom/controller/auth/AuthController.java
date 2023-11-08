package com.amacom.amacom.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.auth.ChangePasswordDTO;
import com.amacom.amacom.dto.auth.LoginRequestDTO;
import com.amacom.amacom.dto.auth.RegisterRequestDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.auth.AuthResponseMapper;
import com.amacom.amacom.mapper.auth.LoginRequestMapper;
import com.amacom.amacom.mapper.auth.RegisterRequestMapper;
import com.amacom.amacom.model.auth.AuthResponse;
import com.amacom.amacom.model.auth.ChangePasswordRequest;
import com.amacom.amacom.model.auth.LoginRequest;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.repository.auth.IUserRepository;
import com.amacom.amacom.service.interfaces.IUserService;
import com.amacom.amacom.service.interfaces.auth.IAuthService;
import com.amacom.amacom.service.interfaces.auth.IPasswordResetService;
import com.amacom.amacom.util.ErrorCodes;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService IAuthService;

    private IPasswordResetService passwordResetService;

    private IUserService usersService;

    private IUserRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            AuthResponse response = this.IAuthService
                    .login(LoginRequestMapper.INSTANCE.toLoginRequest(loginRequestDTO));
            if (response == null) {
                return new ResponseEntity<>(new ErrorDTO("Wrong credentials."), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new SuccessDTO(AuthResponseMapper.INSTANCE.toAuthResponseDTO(response)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            /// Validate old password
            var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = User.class.cast(authData);
            LoginRequest loginRequest = LoginRequest.buildLoginRequest(user.getUsername(),
                    changePasswordDTO.getOldPassword());
            if (this.IAuthService.validateCredentials(loginRequest)) {
                /// Change current user password
                ChangePasswordRequest request = ChangePasswordRequest.buildRequest(user.getId(),
                        changePasswordDTO.getNewPassword());

                boolean response = this.usersService
                        .changePassword(request);
                if (response) {
                    return new ResponseEntity<>(new SuccessDTO(true), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(new ErrorDTO(false, "Bad credentials."),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        if (this.usersService.findByEmail(registerRequestDTO.getEmail()) != null) {
            return new ResponseEntity<>(
                    new ErrorDTO(ErrorCodes.UNIQUE_VALUES_REQUIRED,
                            "Email already in use"),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            AuthResponse response = this.IAuthService
                    .register(RegisterRequestMapper.INSTANCE.toRegisterRequest(registerRequestDTO));
            if (response == null) {
                return new ResponseEntity<>(
                        new ErrorDTO(ErrorCodes.ERROR_CREATING_RECORD),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new SuccessDTO(AuthResponseMapper.INSTANCE.toAuthResponseDTO(response)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ErrorDTO(ErrorCodes.ERROR_CREATING_RECORD),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/enviarCode")
    public ResponseEntity<String> resetPassword(@RequestParam(name = "email") String email) {
        User user = usersService.findByEmail(email);
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
            @RequestParam(name = "code") String code,
            @RequestParam(name = "password") String password) {
        User user = usersService.findByEmail(email);
        var token = passwordResetService.isCodeValid(email, code);
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
    public void setUsersService(IUserService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    public void setUsuarioRepository(IUserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setPasswordResetService(IPasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }
}
