package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.InterventionHasActivities;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IInterventionHasActivitiesService {

    InterventionHasActivities findById(UUID id);

    InterventionHasActivities create(InterventionHasActivities interventionHasActivities);

    InterventionHasActivities update(InterventionHasActivities interventionHasActivities);

    void deleteById(UUID id);

    InterventionHasActivities getEntityFromUUID(UUID uuid);


}
