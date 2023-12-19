package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.InterventionHasActivities;

@Service
public interface IInterventionHasActivitiesService {

    InterventionHasActivities findById(UUID id);

    InterventionHasActivities create(InterventionHasActivities interventionHasActivities);

    InterventionHasActivities update(InterventionHasActivities interventionHasActivities);

    void deleteById(UUID id);

    InterventionHasActivities getEntityFromUUID(UUID uuid);

}
