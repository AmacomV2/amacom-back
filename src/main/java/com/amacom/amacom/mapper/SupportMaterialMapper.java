package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.SupportMaterialDTO;
import com.amacom.amacom.model.SupportMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupportMaterialMapper {

    SupportMaterialMapper INSTANCE = Mappers.getMapper(SupportMaterialMapper.class);

    SupportMaterial toSupportMaterial(SupportMaterialDTO supportMaterialDTO);

    SupportMaterialDTO toSupportMaterialDTO(SupportMaterial supportMaterial);
}
