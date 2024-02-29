package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Intervention;

@Service
public interface IInterventionService {

    Intervention findById(UUID id);

    Page<Intervention> findIntervention(UUID interventionId, Pageable pageable);

    Intervention create(Intervention intervention);

    Intervention update(Intervention intervention);

    void deleteById(UUID id);

    Intervention getEntityFromUUID(UUID uuid);

}
