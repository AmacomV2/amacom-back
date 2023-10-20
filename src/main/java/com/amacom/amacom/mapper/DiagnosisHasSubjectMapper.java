package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.DiagnosisHasSubjectDTO;
import com.amacom.amacom.model.DiagnosisHasSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiagnosisHasSubjectMapper {

    DiagnosisHasSubjectMapper INSTANCE = Mappers.getMapper(DiagnosisHasSubjectMapper.class);

    DiagnosisHasSubject toDiagnosisHasSubject(DiagnosisHasSubjectDTO diagnosisHasSubjectDTO);

    @Mapping(target = "idSubject", source = "subject.id")
    @Mapping(target = "nombreSubject", source = "subject.nombre")
    @Mapping(target = "idDiagnosis", source = "diagnosis.id")
    DiagnosisHasSubjectDTO toDiagnosisHasSubjectDTO(DiagnosisHasSubject diagnosisHasSubject);
}
