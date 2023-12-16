package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.auth.ChangePasswordRequest;
import com.amacom.amacom.model.auth.Rol;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.repository.IPersonRepository;
import com.amacom.amacom.repository.IRolRepository;
import com.amacom.amacom.repository.auth.IUserRepository;
import com.amacom.amacom.service.interfaces.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private IUserRepository userRepository;
    private IPersonRepository personRepository;
    private IRolRepository rolRepository;

    @Override
    public User getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return userRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<User> findUsers(UUID personId, String query, Pageable pageable) {

        Page<User> usersPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("username").ascending().and(Sort.by("createdAt").descending()));
            usersPage = this.userRepository.findUser(personId, query, pageableDefault);
        } else {
            usersPage = this.userRepository.findUser(personId, query, pageable);
        }
        return usersPage;
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findUserById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public User updateUser(User user) {
        this.validateRegistration(user);
        var userDB = this.userRepository.findById(user.getId()).orElseThrow(DataNotFoundException::new);
        userDB.setRol(user.getRol());
        userDB.setEnumRol(user.getRol().getEnumRol());
        userDB.setUsername(user.getUsername());
        userDB.setEmail(user.getEmail());
        userDB.setUpdatedAt(new Date());
        return this.userRepository.save(userDB);
    }

    @Override
    public void deleteUserById(UUID id) {
        var userDB = this.userRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.userRepository.deleteById(userDB.getId());
    }

    public void validateRegistration(User usuario) {

        var existsSimilar = this.userRepository.existsByUsernameOrEmail(usuario.getId(), usuario.getUsername(),
                usuario.getEmail());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Email or username already in use.");

        var existsSimilarByPersonId = this.userRepository.existsByPersonId(usuario.getId(),
                usuario.getPerson().getId());
        if (Boolean.TRUE.equals(existsSimilarByPersonId))
            throw new ValidationException("Ya existe un registro diferente para esta person.");
    }

    @Override
    public User findByEmail(String email) {
        User usuario = this.userRepository.findByEmail(email);
        if (usuario != null) {
            return usuario;
        } else {
            throw new ValidationException("El email proporcionado no se encuentra registrado en la base de datos.");
        }
    }

    @Override
    public boolean validateEmail(String email) {
        User usuario = this.userRepository.findByEmail(email);
        return usuario != null;
    }

    @Transactional
    @Override
    public User newUser(User user) {

        var person = this.personRepository.findById(user.getPerson().getId()).orElseThrow(DataNotFoundException::new);

        Rol rol;
        if (user.getRol().getId() != null) {
            rol = this.rolRepository.findById(user.getRol().getId()).orElse(null);
        } else {
            rol = this.rolRepository.findRolByDescription("USUARIO");
        }

        User userDB = User.builder()
                .id(UUID.randomUUID())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .person(person)
                .rol(rol)
                .enumRol(rol.getEnumRol())
                .createdAt(new Date())
                .build();
        this.userRepository.save(userDB);

        return userDB;

    }

    @Override
    public boolean changePassword(ChangePasswordRequest request) {
        User user = this.userRepository.findById(request.getId()).orElseThrow(DataNotFoundException::new);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Autowired
    public void setUserRepository(IUserRepository usersRepository) {
        this.userRepository = usersRepository;
    }
}
