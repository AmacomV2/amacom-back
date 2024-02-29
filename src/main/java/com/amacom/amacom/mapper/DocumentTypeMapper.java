package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.DocumentTypeDTO;
import com.amacom.amacom.model.DocumentType;

@Mapper
public interface DocumentTypeMapper {

    DocumentTypeMapper INSTANCE = Mappers.getMapper(DocumentTypeMapper.class);

    // DTO TO ENTITY

    DocumentType toDocumentType(DocumentTypeDTO documentTypeDTO);

    // ENTITY TO DTO

    DocumentTypeDTO toDocumentTypeDTO(DocumentType documentType);
}
