package com.amacom.amacom.service.impl.auth;

import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.auth.ERole;
import com.amacom.amacom.model.auth.LoginRequest;
import com.amacom.amacom.model.auth.RegisterRequest;
import com.amacom.amacom.model.auth.Usuario;
import com.amacom.amacom.model.auth.AuthResponse;
import com.amacom.amacom.repository.IPersonaRepository;
import com.amacom.amacom.service.interfaces.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.amacom.amacom.exception.DataNotFoundException;
import org.springframework.security.core.GrantedAuthority;

import com.amacom.amacom.util.Jwt.JwtService;

import com.amacom.amacom.repository.auth.IUsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private IPersonaRepository personaRepository;

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow(DataNotFoundException::new);
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = jwtService.getAccessToken(user, roles);
        return AuthResponse.builder()
                .accesToken(token)
                .refreshToken(jwtService.getRefreshToken(user, roles))
                .build();
    }



    @Transactional
    public AuthResponse register(RegisterRequest request) {

        var persona = this.personaRepository.findById(request.getIdPersona()).orElseThrow(DataNotFoundException::new);

        this.validarRegistro(request);
        Usuario user = Usuario.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode( request.getPassword()))
            .persona(persona)
            .enumRol(ERole.ROLE_USER)
            .idRol(4L)
            .fechaHoraCreacion(new Date())
            .build();
        usuarioRepository.save(user);

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return AuthResponse.builder()
            .accesToken(jwtService.getAccessToken(user, roles ))
            .build();

    }

    private void validarRegistro(RegisterRequest request){

        var existsSimilar = this.usuarioRepository.existsByUsernameOrEmail(null, request.getUsername(), request.getEmail());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un usuario con este username o este email.");

        var existsSimilarByIdPersona = this.usuarioRepository.existsByIdPersona(null, request.getIdPersona());
        if (Boolean.TRUE.equals(existsSimilarByIdPersona))
            throw new ValidacionException("Ya existe un registro para esta persona.");
    }

    @Bean
    public List<GrantedAuthority> rolesMapping() {
        return Arrays.stream(ERole.values())
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
}
