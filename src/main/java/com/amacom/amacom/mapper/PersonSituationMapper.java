package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.model.PersonSituation;

@Mapper
public interface PersonSituationMapper {

    PersonSituationMapper INSTANCE = Mappers.getMapper(PersonSituationMapper.class);

    PersonSituation toPersonSituation(PersonSituationDTO personSituationDTO);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "userId", source = "usuario.id")
    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "situationTypeId", source = "situationType.id")
    PersonSituationDTO toPersonSituationDTO(PersonSituation personSituation);
}