package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.EventHasPersonsDTO;
import com.amacom.amacom.model.EventHasPersons;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventHasPersonsMapper {

    EventHasPersonsMapper INSTANCE = Mappers.getMapper(EventHasPersonsMapper.class);

    EventHasPersons toEventHasPersons(EventHasPersonsDTO eventHasPersonsDTO);

    @Mapping(target = "idPersona", source = "persona.id")
    EventHasPersonsDTO toEventHasPersonsDTO(EventHasPersons eventHasPersons);
}
