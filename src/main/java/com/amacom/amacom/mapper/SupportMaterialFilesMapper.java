package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.SupportMaterialFilesDTO;
import com.amacom.amacom.model.SupportMaterialFiles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface SupportMaterialFilesMapper {

    SupportMaterialFilesMapper INSTANCE = Mappers.getMapper(SupportMaterialFilesMapper.class);

    SupportMaterialFiles toSupportMaterialFiles(SupportMaterialFilesDTO supportMaterialFilesDTO);

    @Mapping(target = "idSupportMaterial", source = "supportMaterial.id")
    SupportMaterialFilesDTO toSupportMaterialFilesDTO(SupportMaterialFiles supportMaterialFiles);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("path", "path");
        clavesToSort.put("createdAt", "createdAt");
        return clavesToSort;
    }

}
