package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Intervention;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IInterventionRepository;
import com.amacom.amacom.service.interfaces.IInterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class InterventionServiceImpl implements IInterventionService {

    private IInterventionRepository interventionRepository;

    private EntityManager entityManager;

    @Override
    public Intervention getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return interventionRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }


    @Override
    public Intervention findById(UUID id) {
        return this.interventionRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Intervention create(Intervention intervention) {
        intervention.setId(UUID.randomUUID());
        intervention.setFechaHoraCreacion(new Date());
        var interventionBD = this.interventionRepository.save(intervention);
        this.entityManager.flush();
        this.entityManager.refresh(interventionBD);
        return interventionBD;
    }

    @Override
    public Intervention update(Intervention intervention) {
        var interventionBD = this.interventionRepository.findById(intervention.getId()).orElseThrow(DataNotFoundException::new);
        interventionBD.setDiagnosis(intervention.getDiagnosis());
        interventionBD.setFechaHoraModificacion(new Date());
        return this.interventionRepository.save(interventionBD);
    }


    @Override
    public void deleteById(UUID id) {
        var interventionBD = this.interventionRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.interventionRepository.deleteById(interventionBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setInterventionRepository(IInterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }

}
