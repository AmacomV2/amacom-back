package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.DiagnosisDTO;
import com.amacom.amacom.model.Diagnosis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiagnosisMapper {

    DiagnosisMapper INSTANCE = Mappers.getMapper(DiagnosisMapper.class);

    Diagnosis toDiagnosis(DiagnosisDTO diagnosisDTO);

    @Mapping(target = "idPersonSituation", source = "personSituation.id")
    DiagnosisDTO toDiagnosisDTO(Diagnosis diagnosis);
}
