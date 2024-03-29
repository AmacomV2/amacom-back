package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.LogBookDTO;
import com.amacom.amacom.model.LogBook;

@Mapper
public interface LogBookMapper {

    LogBookMapper INSTANCE = Mappers.getMapper(LogBookMapper.class);

    LogBook toLogBook(LogBookDTO logBookDTO);

    @Mapping(target = "personId", source = "person.id")
    LogBookDTO toLogBookDTO(LogBook logBook);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("name", "name");
        keysToSort.put("description", "description");
        return keysToSort;
    }

}
