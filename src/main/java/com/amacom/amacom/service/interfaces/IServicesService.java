package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IServicesService {

    Services findById(UUID id);

    Page<Services> findServices(String query, Pageable pageable);

    Services create(Services services);

    Services update(Services services);

    void deleteById(UUID id);

    Services getEntityFromUUID(UUID uuid);


}
