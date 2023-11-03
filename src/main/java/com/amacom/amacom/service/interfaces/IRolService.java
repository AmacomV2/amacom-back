package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.auth.Rol;

@Service
public interface IRolService {

    List<Rol> getAll();

    Rol getEntityFromUUID(UUID uuid);

}
