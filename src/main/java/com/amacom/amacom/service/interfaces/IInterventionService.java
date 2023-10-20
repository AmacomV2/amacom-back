package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Intervention;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IInterventionService {

    Intervention findById(UUID id);

    Intervention create(Intervention intervention);

    Intervention update(Intervention intervention);

    void deleteById(UUID id);

    Intervention getEntityFromUUID(UUID uuid);


}
