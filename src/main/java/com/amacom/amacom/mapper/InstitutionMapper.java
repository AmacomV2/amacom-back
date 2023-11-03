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
    InstitutionDTO toInstitutionDTO(Institution institution);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("name", "name");
        clavesToSort.put("description", "description");
        clavesToSort.put("createdAt", "createdAt");
        return clavesToSort;
    }

}
