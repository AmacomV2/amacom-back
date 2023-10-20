package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.InterventionDTO;
import com.amacom.amacom.model.Intervention;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterventionMapper {

    InterventionMapper INSTANCE = Mappers.getMapper(InterventionMapper.class);

    Intervention toIntervention(InterventionDTO interventionDTO);

    @Mapping(target = "idDiagnosis", source = "diagnosis.id")
    InterventionDTO toInterventionDTO(Intervention intervention);
}
