package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PersonaDTO;
import com.amacom.amacom.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

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


    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("nombreAndApellido", "nombreAndApellido");
        clavesToSort.put("documento", "documento");
        clavesToSort.put("nombreTipoDocumento", "tipoDocumento.nombre");
        clavesToSort.put("nombreGenero", "genero.nombre");
        clavesToSort.put("nombreEstadoCivil", "estadoCivil.nombre");
        clavesToSort.put("fechaNacimiento", "fechaNacimiento");
        clavesToSort.put("fechaHoraCreacion", "fechaHoraCreacion");
        return clavesToSort;
    }
}
