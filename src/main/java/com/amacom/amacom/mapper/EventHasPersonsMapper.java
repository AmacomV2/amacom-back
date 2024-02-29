package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.EventHasPersonsDTO;
import com.amacom.amacom.model.EventHasPersons;

@Mapper
public interface EventHasPersonsMapper {

    EventHasPersonsMapper INSTANCE = Mappers.getMapper(EventHasPersonsMapper.class);

    EventHasPersons toEventHasPersons(EventHasPersonsDTO eventHasPersonsDTO);

    @Mapping(target = "personName", source = "person.name")
    @Mapping(target = "name", source = "event.name")
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "eventId", source = "event.id")
    EventHasPersonsDTO toEventHasPersonsDTO(EventHasPersons eventHasPersons);
}
