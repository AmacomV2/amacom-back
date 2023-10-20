package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.SupportMaterialHasSubjectDTO;
import com.amacom.amacom.model.SupportMaterialHasSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupportMaterialHasSubjectMapper {

    SupportMaterialHasSubjectMapper INSTANCE = Mappers.getMapper(SupportMaterialHasSubjectMapper.class);

    SupportMaterialHasSubject toSupportMaterialHasSubject(SupportMaterialHasSubjectDTO supportMaterialHasSubjectDTO);

    @Mapping(target = "idSubject", source = "subject.id")
    @Mapping(target = "idSupportMaterial", source = "supportMaterial.id")
    SupportMaterialHasSubjectDTO toSupportMaterialHasSubjectDTO(SupportMaterialHasSubject supportMaterialHasSubject);
}
