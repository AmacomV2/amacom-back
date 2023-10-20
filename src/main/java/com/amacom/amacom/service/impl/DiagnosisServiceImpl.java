package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Diagnosis;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IDiagnosisRepository;
import com.amacom.amacom.service.interfaces.IDiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class DiagnosisServiceImpl implements IDiagnosisService {

    private IDiagnosisRepository diagnosisRepository;

    private EntityManager entityManager;


    @Override
    public Diagnosis getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return diagnosisRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Diagnosis findById(UUID id) {
        return this.diagnosisRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Diagnosis create(Diagnosis diagnosis) {
        diagnosis.setId(UUID.randomUUID());
        var diagnosisBD = this.diagnosisRepository.save(diagnosis);
        this.entityManager.flush();
        this.entityManager.refresh(diagnosisBD);
        return diagnosisBD;
    }

    @Override
    public Diagnosis update(Diagnosis diagnosis) {
        var diagnosisBD = this.diagnosisRepository.findById(diagnosis.getId()).orElseThrow(DataNotFoundException::new);
        diagnosisBD.setPersonSituation(diagnosis.getPersonSituation());
        diagnosisBD.setResultadoConsulta(diagnosis.getResultadoConsulta());
        diagnosisBD.setEEstadoConsulta(diagnosis.getEEstadoConsulta());
        diagnosisBD.setEAlertaConsulta(diagnosis.getEAlertaConsulta());
        return this.diagnosisRepository.save(diagnosisBD);
    }

    @Override
    public void deleteById(UUID id) {
        var diagnosisBD = this.diagnosisRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.diagnosisRepository.deleteById(diagnosisBD.getId());
    }


    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setDiagnosisRepository(IDiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

}
