package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.SupportMaterialDTO;
import com.amacom.amacom.model.SupportMaterial;

@Mapper
public interface SupportMaterialMapper {

    SupportMaterialMapper INSTANCE = Mappers.getMapper(SupportMaterialMapper.class);

    SupportMaterial toSupportMaterial(SupportMaterialDTO supportMaterialDTO);

    SupportMaterialDTO toSupportMaterialDTO(SupportMaterial supportMaterial);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("name", "name");
        clavesToSort.put("description", "description");
        clavesToSort.put("createdAt", "createdAt");
        return clavesToSort;
    }

}
