package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.InstitutionServiceDTO;
import com.amacom.amacom.model.InstitutionService;

@Mapper
public interface InstitutionServiceMapper {

    InstitutionServiceMapper INSTANCE = Mappers.getMapper(InstitutionServiceMapper.class);

    InstitutionService toInstitutionService(InstitutionServiceDTO institutionServiceDTO);

    @Mapping(target = "idServices", source = "services.id")
    @Mapping(target = "idInstitution", source = "institution.id")
    InstitutionServiceDTO toInstitutionServiceDTO(InstitutionService institutionService);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("idServices", "services.id");
        return keysToSort;
    }

}
