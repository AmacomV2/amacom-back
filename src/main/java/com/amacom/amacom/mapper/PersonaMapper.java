package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PersonaDTO;
import com.amacom.amacom.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {

    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    //DTO TO ENTITY

    Persona toPersona(PersonaDTO personaDTO);

    //ENTITY TO DTO

    @Mapping(target = "nombreTipoDocumento", source = "tipoDocumento.nombre")
    @Mapping(target = "nombreGenero", source = "genero.nombre")
    @Mapping(target = "nombreEstadoCivil", source = "estadoCivil.nombre")
    @Mapping(target = "idTipoDocumento", source = "tipoDocumento.id")
    @Mapping(target = "idGenero", source = "genero.id")
    @Mapping(target = "idEstadoCivil", source = "estadoCivil.id")
    PersonaDTO toPersonaDTO(Persona persona);

}
