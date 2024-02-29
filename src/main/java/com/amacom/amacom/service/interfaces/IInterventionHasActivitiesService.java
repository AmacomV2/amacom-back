package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.InterventionHasActivities;

@Service
public interface IInterventionHasActivitiesService {

    InterventionHasActivities findById(UUID id);

    Page<InterventionHasActivities> findInterventionActivities(UUID interventionId, String query, boolean status,
            Pageable pageable);

    InterventionHasActivities create(InterventionHasActivities interventionHasActivities);

    InterventionHasActivities update(InterventionHasActivities interventionHasActivities);

    void deleteById(UUID id);

    InterventionHasActivities getEntityFromUUID(UUID uuid);

}
