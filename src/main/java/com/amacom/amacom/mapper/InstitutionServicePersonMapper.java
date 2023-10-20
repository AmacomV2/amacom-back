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

@Mapper
public interface InstitutionServicePersonMapper {

    InstitutionServicePersonMapper INSTANCE = Mappers.getMapper(InstitutionServicePersonMapper.class);

    InstitutionServicePerson toInstitutionServicePerson(InstitutionServicePersonDTO institutionServicePersonDTO);

    @Mapping(target = "idPersona", source = "persona.id")
    @Mapping(target = "idInstitutionService", source = "institutionService.id")
    InstitutionServicePersonDTO toInstitutionServicePersonDTO(InstitutionServicePerson institutionServicePerson);

}
