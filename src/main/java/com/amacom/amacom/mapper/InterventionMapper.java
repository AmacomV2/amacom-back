package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.InterventionDTO;
import com.amacom.amacom.model.Intervention;

@Mapper
public interface InterventionMapper {

    InterventionMapper INSTANCE = Mappers.getMapper(InterventionMapper.class);

    Intervention toIntervention(InterventionDTO interventionDTO);

    @Mapping(target = "diagnosisId", source = "diagnosis.id")
    InterventionDTO toInterventionDTO(Intervention intervention);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}
