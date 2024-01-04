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

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("name", "name");
        keysToSort.put("description", "description");
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}
