package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.SupportMaterialFilesDTO;
import com.amacom.amacom.model.SupportMaterialFiles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupportMaterialFilesMapper {

    SupportMaterialFilesMapper INSTANCE = Mappers.getMapper(SupportMaterialFilesMapper.class);

    SupportMaterialFiles toSupportMaterialFiles(SupportMaterialFilesDTO supportMaterialFilesDTO);

    @Mapping(target = "idSupportMaterial", source = "supportMaterial.id")
    SupportMaterialFilesDTO toSupportMaterialFilesDTO(SupportMaterialFiles supportMaterialFiles);
}
