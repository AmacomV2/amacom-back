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

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("name", "name");
        keysToSort.put("description", "description");
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}
