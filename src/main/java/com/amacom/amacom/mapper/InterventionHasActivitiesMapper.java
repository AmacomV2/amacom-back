package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.InterventionHasActivitiesDTO;
import com.amacom.amacom.model.InterventionHasActivities;

@Mapper
public interface InterventionHasActivitiesMapper {

    InterventionHasActivitiesMapper INSTANCE = Mappers.getMapper(InterventionHasActivitiesMapper.class);

    InterventionHasActivities toInterventionHasActivities(InterventionHasActivitiesDTO interventionHasActivities);

    @Mapping(target = "idActivity", source = "activity.id")
    @Mapping(target = "idIntervention", source = "intervention.id")
    InterventionHasActivitiesDTO toInterventionHasActivitiesDTO(InterventionHasActivities interventionHasActivities);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }
}
