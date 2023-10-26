package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.SupportMaterialDTO;
import com.amacom.amacom.model.SupportMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface SupportMaterialMapper {

    SupportMaterialMapper INSTANCE = Mappers.getMapper(SupportMaterialMapper.class);

    SupportMaterial toSupportMaterial(SupportMaterialDTO supportMaterialDTO);

    SupportMaterialDTO toSupportMaterialDTO(SupportMaterial supportMaterial);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("nombre", "nombre");
        clavesToSort.put("descripcion", "descripcion");
        clavesToSort.put("fechaHoraCreacion", "fechaHoraCreacion");
        return clavesToSort;
    }

}
