package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.EventDTO;
import com.amacom.amacom.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEvent(EventDTO eventDTO);

    @Mapping(target = "nombreTipoEvento", source = "tipoEvento.nombre")
    @Mapping(target = "idTipoEvento", source = "tipoEvento.id")
    @Mapping(target = "idUsuarioCrea", source = "usuario.id")
    EventDTO toEventDTO(Event event);
}
