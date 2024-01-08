package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.InstitutionDTO;
import com.amacom.amacom.model.Institution;

@Mapper
public interface InstitutionMapper {

    InstitutionMapper INSTANCE = Mappers.getMapper(InstitutionMapper.class);

    Institution toInstitution(InstitutionDTO institutionDTO);

    @Mapping(target = "institutionTypeId", source = "institutionType.id")
    @Mapping(target = "institutionTypeName", source = "institutionType.name")
    InstitutionDTO toInstitutionDTO(Institution institution);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("name", "name");
        keysToSort.put("description", "description");
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}
