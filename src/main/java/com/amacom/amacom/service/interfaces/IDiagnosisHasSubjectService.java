package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.DiagnosisHasSubject;
import com.amacom.amacom.model.Genero;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IDiagnosisHasSubjectService {

    DiagnosisHasSubject findById(UUID id);

    DiagnosisHasSubject create(DiagnosisHasSubject diagnosisHasSubject);

    DiagnosisHasSubject update(DiagnosisHasSubject diagnosisHasSubject);

    void deleteById(UUID id);

    DiagnosisHasSubject getEntityFromUUID(UUID uuid);


}
