package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Phase;

@Service
public interface IPhaseService {

    Phase findById(UUID id);

    Phase create(Phase phrase);

    Phase update(Phase phrase);

    void deleteById(UUID id);

    Phase getEntityFromUUID(UUID uuid);

}
