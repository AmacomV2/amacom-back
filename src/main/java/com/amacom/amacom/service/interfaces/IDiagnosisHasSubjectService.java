package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.DiagnosisHasSubject;

@Service
public interface IDiagnosisHasSubjectService {

    DiagnosisHasSubject findById(UUID id);

    DiagnosisHasSubject create(DiagnosisHasSubject diagnosisHasSubject);

    DiagnosisHasSubject update(DiagnosisHasSubject diagnosisHasSubject);

    void deleteById(UUID id);

    DiagnosisHasSubject getEntityFromUUID(UUID uuid);

}
