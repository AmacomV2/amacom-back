package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.InterventionHasActivities;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IInterventionHasActivitiesRepository;
import com.amacom.amacom.service.interfaces.IInterventionHasActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

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
        var interventionHasActivitiesBD = this.interventionHasActivitiesRepository.findById(interventionHasActivities.getId()).orElseThrow(DataNotFoundException::new);
        interventionHasActivitiesBD.setActivity(interventionHasActivities.getActivity());
        interventionHasActivitiesBD.setIntervention(interventionHasActivities.getIntervention());
        interventionHasActivitiesBD.setEstado(interventionHasActivities.getEstado());
        interventionHasActivitiesBD.setDescripcion(interventionHasActivities.getDescripcion());
        return this.interventionHasActivitiesRepository.save(interventionHasActivitiesBD);
    }

    @Override
    public void deleteById(UUID id) {
        var interventionHasActivitiesBD = this.interventionHasActivitiesRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.interventionHasActivitiesRepository.deleteById(interventionHasActivitiesBD.getId());
    }


    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setInterventionHasActivitiesRepository(IInterventionHasActivitiesRepository interventionHasActivitiesRepository) {
        this.interventionHasActivitiesRepository = interventionHasActivitiesRepository;
    }
}
