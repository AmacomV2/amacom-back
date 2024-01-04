package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.SupportMaterialFilesDTO;
import com.amacom.amacom.model.SupportMaterialFiles;

@Mapper
public interface SupportMaterialFilesMapper {

    SupportMaterialFilesMapper INSTANCE = Mappers.getMapper(SupportMaterialFilesMapper.class);

    SupportMaterialFiles toSupportMaterialFiles(SupportMaterialFilesDTO supportMaterialFilesDTO);

    @Mapping(target = "idSupportMaterial", source = "supportMaterial.id")
    SupportMaterialFilesDTO toSupportMaterialFilesDTO(SupportMaterialFiles supportMaterialFiles);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("path", "path");
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}
