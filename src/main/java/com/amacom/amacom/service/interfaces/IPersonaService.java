package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IPersonaService {

    Page<Persona> findPersona( String query, Pageable pageable);

    Persona getPersonaFromUUID(UUID idPersona);

    List<Persona> getAll();

    Persona findPersonaById(UUID id);

    Persona createPersona(Persona persona);

    Persona updatePersona(Persona persona);

    void deletePersonaById(UUID id);


}
