package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.repository.auth.IUserRepository;
import com.amacom.amacom.service.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

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
            usersPage = this.userRepository.findUsuario(personId, query, pageableDefault);
        } else {
            usersPage = this.userRepository.findUsuario(personId, query, pageable);
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
        var usuario = this.userRepository.findByEmail(email);
        if (usuario != null) {
            return usuario;
        } else {
            throw new ValidationException("El email proporcionado no se encuentra registrado en la base de datos.");
        }
    }

    @Autowired
    public void setUsuarioRepository(IUserRepository usuarioRepository) {
        this.userRepository = usuarioRepository;
    }
}
