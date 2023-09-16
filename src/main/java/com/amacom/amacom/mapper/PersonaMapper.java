package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PersonaDTO;
import com.amacom.amacom.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {

    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    //DTO TO ENTITY
    Persona toPersona(PersonaDTO personaDTO);

    //ENTITY TO DTO

    PersonaDTO toPersonaDTO(Persona persona);

}
