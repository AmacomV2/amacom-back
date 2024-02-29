package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.DiagnosisHasSubjectDTO;
import com.amacom.amacom.model.DiagnosisHasSubject;

@Mapper
public interface DiagnosisHasSubjectMapper {

    DiagnosisHasSubjectMapper INSTANCE = Mappers.getMapper(DiagnosisHasSubjectMapper.class);

    DiagnosisHasSubject toDiagnosisHasSubject(DiagnosisHasSubjectDTO diagnosisHasSubjectDTO);

    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "subjectName", source = "subject.name")
    @Mapping(target = "diagnosisId", source = "diagnosis.id")
    DiagnosisHasSubjectDTO toDiagnosisHasSubjectDTO(DiagnosisHasSubject diagnosisHasSubject);
}
