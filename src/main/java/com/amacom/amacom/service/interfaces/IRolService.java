package com.amacom.amacom.service.interfaces;


import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.auth.Rol;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IRolService {

    List<Rol> getAll();

    Rol getEntityFromUUID(UUID uuid);

}
