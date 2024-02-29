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
import com.amacom.amacom.model.InterventionHasActivities;
import com.amacom.amacom.repository.IInterventionHasActivitiesRepository;
import com.amacom.amacom.service.interfaces.IInterventionHasActivitiesService;

@Service
public class InterventionHasActivitiesServiceImpl implements IInterventionHasActivitiesService {

    private IInterventionHasActivitiesRepository interventionHasActivitiesRepository;

    private EntityManager entityManager;

    @Override
    public InterventionHasActivities getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return interventionHasActivitiesRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public InterventionHasActivities findById(UUID id) {
        return this.interventionHasActivitiesRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public InterventionHasActivities create(InterventionHasActivities interventionHasActivities) {
        interventionHasActivities.setId(UUID.randomUUID());
        var interventionHasActivitiesBD = this.interventionHasActivitiesRepository.save(interventionHasActivities);
        this.entityManager.flush();
        this.entityManager.refresh(interventionHasActivitiesBD);
        return interventionHasActivitiesBD;
    }

    @Override
    public InterventionHasActivities update(InterventionHasActivities interventionHasActivities) {
        var interventionHasActivitiesBD = this.interventionHasActivitiesRepository
                .findById(interventionHasActivities.getId()).orElseThrow(DataNotFoundException::new);
        interventionHasActivitiesBD.setActivity(interventionHasActivities.getActivity());
        interventionHasActivitiesBD.setIntervention(interventionHasActivities.getIntervention());
        interventionHasActivitiesBD.setStatus(interventionHasActivities.getStatus());
        interventionHasActivitiesBD.setDescription(interventionHasActivities.getDescription());
        return this.interventionHasActivitiesRepository.save(interventionHasActivitiesBD);
    }

    @Override
    public void deleteById(UUID id) {
        var interventionHasActivitiesBD = this.interventionHasActivitiesRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        this.interventionHasActivitiesRepository.deleteById(interventionHasActivitiesBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setInterventionHasActivitiesRepository(
            IInterventionHasActivitiesRepository interventionHasActivitiesRepository) {
        this.interventionHasActivitiesRepository = interventionHasActivitiesRepository;
    }

    @Override
    public Page<InterventionHasActivities> findInterventionActivities(UUID interventionId, String query, boolean status,
            Pageable pageable) {
        Page<InterventionHasActivities> interventionsPage;

        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("createdAt").descending());
        }

        interventionsPage = this.interventionHasActivitiesRepository.findInterventionActivities(interventionId, query,
                status, pageable);

        return interventionsPage;
    }
}
