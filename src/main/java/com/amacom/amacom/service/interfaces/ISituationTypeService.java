package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.SituationType;

@Service
public interface ISituationTypeService {

    SituationType findById(UUID id);

    SituationType create(SituationType situationType);

    SituationType update(SituationType situationType);

    void deleteById(UUID id);

    SituationType getEntityFromUUID(UUID uuid);

}
