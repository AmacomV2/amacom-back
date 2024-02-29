package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.DiagnosisHasSubject;
import com.amacom.amacom.repository.IDiagnosisHasSubjectRepository;
import com.amacom.amacom.service.interfaces.IDiagnosisHasSubjectService;

@Service
public class DiagnosisHasSubjectServiceImpl implements IDiagnosisHasSubjectService {

    private IDiagnosisHasSubjectRepository diagnosisHasSubjectRepository;

    private EntityManager entityManager;

    @Override
    public DiagnosisHasSubject getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return diagnosisHasSubjectRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public DiagnosisHasSubject findById(UUID id) {
        return this.diagnosisHasSubjectRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public DiagnosisHasSubject create(DiagnosisHasSubject diagnosisHasSubject) {
        diagnosisHasSubject.setId(UUID.randomUUID());
        var diagnosisHasSubjectBD = this.diagnosisHasSubjectRepository.save(diagnosisHasSubject);
        this.entityManager.flush();
        this.entityManager.refresh(diagnosisHasSubjectBD);
        return diagnosisHasSubjectBD;
    }

    @Override
    public DiagnosisHasSubject update(DiagnosisHasSubject diagnosisHasSubject) {
        var diagnosisHasSubjectBD = this.diagnosisHasSubjectRepository.findById(diagnosisHasSubject.getId())
                .orElseThrow(DataNotFoundException::new);
        diagnosisHasSubjectBD.setSubject(diagnosisHasSubject.getSubject());
        diagnosisHasSubjectBD.setDiagnosis(diagnosisHasSubject.getDiagnosis());
        return this.diagnosisHasSubjectRepository.save(diagnosisHasSubjectBD);
    }

    @Override
    public void deleteById(UUID id) {
        var diagnosisHasSubjectBD = this.diagnosisHasSubjectRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        this.diagnosisHasSubjectRepository.deleteById(diagnosisHasSubjectBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setDiagnosisHasSubjectRepository(IDiagnosisHasSubjectRepository diagnosisHasSubjectRepository) {
        this.diagnosisHasSubjectRepository = diagnosisHasSubjectRepository;
    }
}
