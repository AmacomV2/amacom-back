package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.model.PersonSituation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonSituationMapper {

    PersonSituationMapper INSTANCE = Mappers.getMapper(PersonSituationMapper.class);

    PersonSituation toPersonSituation(PersonSituationDTO personSituationDTO);

    @Mapping(target = "idPersona", source = "persona.id")
    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "idSubject", source = "subject.id")
    @Mapping(target = "idTipoSituacion", source = "tipoSituacion.id")
    PersonSituationDTO toPersonSituationDTO(PersonSituation personSituation);
}
