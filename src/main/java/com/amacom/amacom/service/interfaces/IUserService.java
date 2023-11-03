package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.auth.User;

@Service
public interface IUserService {

    User getEntityFromUUID(UUID uuid);

    Page<User> findUsers(UUID personId, String query, Pageable pageable);

    List<User> getAll();

    User findUserById(UUID id);

    User updateUser(User user);

    void deleteUserById(UUID id);

    User findByEmail(String email);

}
