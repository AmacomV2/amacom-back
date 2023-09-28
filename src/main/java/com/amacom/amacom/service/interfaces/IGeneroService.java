package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Persona;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IGeneroService {

    List<Genero> getAll();

    Genero findById(UUID id);

    Genero create(Genero genero);

    Genero update(Genero genero);

    void deleteById(UUID id);

    Genero getEntityFromUUID(UUID uuid);
}
