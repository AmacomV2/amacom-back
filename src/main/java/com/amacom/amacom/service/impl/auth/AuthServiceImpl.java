package com.amacom.amacom.service.impl.auth;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.auth.AuthResponse;
import com.amacom.amacom.model.auth.ERole;
import com.amacom.amacom.model.auth.LoginRequest;
import com.amacom.amacom.model.auth.NewUserRequest;
import com.amacom.amacom.model.auth.Rol;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.repository.IPersonRepository;
import com.amacom.amacom.repository.IRolRepository;
import com.amacom.amacom.repository.auth.IUserRepository;
import com.amacom.amacom.service.interfaces.auth.IAuthService;
import com.amacom.amacom.util.Jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Configuration
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository usersRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private IPersonRepository personRepository;

    private IRolRepository rolRepository;

    @Override
    public boolean validateCredentials(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AuthResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            return null;
        }

        UserDetails user = usersRepository.findByUsername(request.getUsername())
                .orElseThrow(DataNotFoundException::new);
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = jwtService.getAccessToken(user, roles);
        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(jwtService.getRefreshToken(user, roles))
                .build();
    }

    // TODO: Remove this shit
    @Transactional
    public AuthResponse register(NewUserRequest request) {

        var person = this.personRepository.findById(request.getPersonId()).orElseThrow(DataNotFoundException::new);

        Rol rol;
        if (request.getIdRol() != null) {
            rol = this.rolRepository.findById(request.getIdRol()).orElse(null);
        } else {
            rol = this.rolRepository.findRolByDescription("USER");
        }

        this.validateRegistration(request);
        User user = User.builder()
                .id(UUID.randomUUID())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .person(person)
                .rol(rol)
                .enumRol(rol.getEnumRol())
                .build();
        usersRepository.save(user);

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return AuthResponse.builder()
                .accessToken(jwtService.getAccessToken(user, roles))
                .build();

    }

    private void validateRegistration(NewUserRequest request) {

        var existsSimilar = this.usersRepository.existsByUsernameOrEmail(null, request.getUsername(),
                request.getEmail());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Email or username already in use.");

        var existsSimilarByPersonId = this.usersRepository.existsByPersonId(null, request.getPersonId());
        if (Boolean.TRUE.equals(existsSimilarByPersonId))
            throw new ValidationException("Person already have a registered user.");
    }

    @Bean
    public List<GrantedAuthority> rolesMapping() {
        return Arrays.stream(ERole.values())
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Autowired
    public void setPersonRepository(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setRolRepository(IRolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

}
