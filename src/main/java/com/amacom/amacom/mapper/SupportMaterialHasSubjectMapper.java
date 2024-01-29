package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.SupportMaterialHasSubjectDTO;
import com.amacom.amacom.model.SupportMaterialHasSubject;

@Mapper
public interface SupportMaterialHasSubjectMapper {

    SupportMaterialHasSubjectMapper INSTANCE = Mappers.getMapper(SupportMaterialHasSubjectMapper.class);

    SupportMaterialHasSubject toSupportMaterialHasSubject(SupportMaterialHasSubjectDTO supportMaterialHasSubjectDTO);

    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "idSupportMaterial", source = "supportMaterial.id")
    @Mapping(target = "subjectName", source = "subject.name")
    @Mapping(target = "subjectDescription", source = "subject.description")
    @Mapping(target = "materialName", source = "supportMaterial.name")
    @Mapping(target = "materialDescription", source = "supportMaterial.description")
    SupportMaterialHasSubjectDTO toSupportMaterialHasSubjectDTO(SupportMaterialHasSubject supportMaterialHasSubject);
}
