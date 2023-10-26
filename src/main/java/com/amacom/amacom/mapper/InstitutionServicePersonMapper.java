package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.InstitutionDTO;
import com.amacom.amacom.dto.InstitutionServiceDTO;
import com.amacom.amacom.dto.InstitutionServicePersonDTO;
import com.amacom.amacom.model.Institution;
import com.amacom.amacom.model.InstitutionService;
import com.amacom.amacom.model.InstitutionServicePerson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface InstitutionServicePersonMapper {

    InstitutionServicePersonMapper INSTANCE = Mappers.getMapper(InstitutionServicePersonMapper.class);

    InstitutionServicePerson toInstitutionServicePerson(InstitutionServicePersonDTO institutionServicePersonDTO);

    @Mapping(target = "idPersona", source = "persona.id")
    @Mapping(target = "idInstitutionService", source = "institutionService.id")
    InstitutionServicePersonDTO toInstitutionServicePersonDTO(InstitutionServicePerson institutionServicePerson);


    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("idInstitutionService", "institutionService.id");
        return clavesToSort;
    }
}
