package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.InstitutionTypeDTO;
import com.amacom.amacom.model.InstitutionType;

@Mapper
public interface InstitutionTypeMapper {

    InstitutionTypeMapper INSTANCE = Mappers.getMapper(InstitutionTypeMapper.class);

    InstitutionType toInstitutionType(InstitutionTypeDTO typeInstitutionDTO);

    InstitutionTypeDTO toInstitutionTypeDTO(InstitutionType typeInstitution);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("name", "name");
        clavesToSort.put("description", "description");
        clavesToSort.put("createdAt", "createdAt");
        return clavesToSort;
    }

}
