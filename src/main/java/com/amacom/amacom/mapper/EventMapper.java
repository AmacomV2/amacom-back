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
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "eventColor", source = "eventType.color")
    EventDTO toEventDTO(Event event);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("eventTypeName", "eventType.name");
        keysToSort.put("name", "name");
        return keysToSort;
    }

}
