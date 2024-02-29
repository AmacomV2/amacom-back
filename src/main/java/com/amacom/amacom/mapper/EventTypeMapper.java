package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.EventTypeDTO;
import com.amacom.amacom.model.EventType;

@Mapper
public interface EventTypeMapper {

    EventTypeMapper INSTANCE = Mappers.getMapper(EventTypeMapper.class);

    EventType toEventType(EventTypeDTO eventTypeDTO);

    EventTypeDTO toEventTypeDTO(EventType eventType);
}
