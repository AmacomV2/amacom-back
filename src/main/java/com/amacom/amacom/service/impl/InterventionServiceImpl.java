package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.Intervention;
import com.amacom.amacom.repository.IInterventionRepository;
import com.amacom.amacom.service.interfaces.IInterventionService;

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
        var interventionBD = this.interventionRepository.save(intervention);
        this.entityManager.flush();
        this.entityManager.refresh(interventionBD);
        return interventionBD;
    }

    @Override
    public Intervention update(Intervention intervention) {
        var interventionBD = this.interventionRepository.findById(intervention.getId())
                .orElseThrow(DataNotFoundException::new);
        interventionBD.setDiagnosis(intervention.getDiagnosis());
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

    @Override
    public Page<Intervention> findIntervention(UUID diagnosisId, Pageable pageable) {
        Page<Intervention> interventionsPage;

        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("createdAt").descending());
        }

        interventionsPage = this.interventionRepository.findInterventions(diagnosisId, pageable);

        return interventionsPage;
    }

}
