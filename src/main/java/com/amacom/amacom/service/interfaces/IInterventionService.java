package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Intervention;

@Service
public interface IInterventionService {

    Intervention findById(UUID id);

    Intervention create(Intervention intervention);

    Intervention update(Intervention intervention);

    void deleteById(UUID id);

    Intervention getEntityFromUUID(UUID uuid);

}
