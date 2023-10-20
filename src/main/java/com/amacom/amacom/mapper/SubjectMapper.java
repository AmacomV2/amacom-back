package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.SubjectDTO;
import com.amacom.amacom.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    Subject toSubject(SubjectDTO subjectDTO);

    @Mapping(target = "idSubjectParent", source = "subjectParent.id")
    @Mapping(target = "idResultadosAsociados", source = "resultadosAsociados.id")
    SubjectDTO toSubjectDTO(Subject subject);
}
