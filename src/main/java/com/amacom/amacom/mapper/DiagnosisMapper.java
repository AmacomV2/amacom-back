package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.DiagnosisDTO;
import com.amacom.amacom.model.Diagnosis;

@Mapper
public interface DiagnosisMapper {

    DiagnosisMapper INSTANCE = Mappers.getMapper(DiagnosisMapper.class);

    Diagnosis toDiagnosis(DiagnosisDTO diagnosisDTO);

    @Mapping(target = "personSituationId", source = "personSituation.id")
    DiagnosisDTO toDiagnosisDTO(Diagnosis diagnosis);
}
