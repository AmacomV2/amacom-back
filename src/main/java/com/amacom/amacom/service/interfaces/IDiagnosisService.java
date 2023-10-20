package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Diagnosis;
import com.amacom.amacom.model.Genero;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IDiagnosisService {

    Diagnosis findById(UUID id);

    Diagnosis create(Diagnosis diagnosis);

    Diagnosis update(Diagnosis diagnosis);

    void deleteById(UUID id);

    Diagnosis getEntityFromUUID(UUID uuid);


}
