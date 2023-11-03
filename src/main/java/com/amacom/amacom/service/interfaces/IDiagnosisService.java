package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Diagnosis;

@Service
public interface IDiagnosisService {

    Diagnosis findById(UUID id);

    Diagnosis create(Diagnosis diagnosis);

    Diagnosis update(Diagnosis diagnosis);

    void deleteById(UUID id);

    Diagnosis getEntityFromUUID(UUID uuid);

}
