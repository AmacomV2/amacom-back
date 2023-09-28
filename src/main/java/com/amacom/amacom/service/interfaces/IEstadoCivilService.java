package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Persona;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IEstadoCivilService {

    List<EstadoCivil> getAll();

    EstadoCivil getEntityFromUUID(UUID uuid);
}
