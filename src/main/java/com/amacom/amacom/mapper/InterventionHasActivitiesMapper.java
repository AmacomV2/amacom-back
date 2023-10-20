package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.InterventionHasActivitiesDTO;
import com.amacom.amacom.model.InterventionHasActivities;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterventionHasActivitiesMapper {

    InterventionHasActivitiesMapper INSTANCE = Mappers.getMapper(InterventionHasActivitiesMapper.class);


    InterventionHasActivities toInterventionHasActivities(InterventionHasActivitiesDTO interventionHasActivities);

    @Mapping(target = "idActivity", source = "activity.id")
    @Mapping(target = "idIntervention", source = "intervention.id")
    InterventionHasActivitiesDTO toInterventionHasActivitiesDTO(InterventionHasActivities interventionHasActivities);
}
