package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.InstitutionServicePersonDTO;
import com.amacom.amacom.model.InstitutionServicePerson;

@Mapper
public interface InstitutionServicePersonMapper {

    InstitutionServicePersonMapper INSTANCE = Mappers.getMapper(InstitutionServicePersonMapper.class);

    InstitutionServicePerson toInstitutionServicePerson(InstitutionServicePersonDTO institutionServicePersonDTO);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "idInstitutionService", source = "institutionService.id")
    InstitutionServicePersonDTO toInstitutionServicePersonDTO(InstitutionServicePerson institutionServicePerson);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("idInstitutionService", "institutionService.id");
        return clavesToSort;
    }
}
