package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.EventDTO;
import com.amacom.amacom.model.Event;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEvent(EventDTO eventDTO);

    @Mapping(target = "eventTypeName", source = "eventType.name")
    @Mapping(target = "eventTypeId", source = "eventType.id")
    @Mapping(target = "createdBy", source = "usuario.id")
    EventDTO toEventDTO(Event event);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("eventTypeName", "eventType.name");
        clavesToSort.put("title", "title");
        return clavesToSort;
    }

}
