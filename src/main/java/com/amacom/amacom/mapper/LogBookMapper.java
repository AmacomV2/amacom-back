package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.LogBookDTO;
import com.amacom.amacom.model.LogBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface LogBookMapper {

    LogBookMapper INSTANCE = Mappers.getMapper(LogBookMapper.class);

    LogBook toLogBook(LogBookDTO logBookDTO);

    @Mapping(target = "idPersona", source = "persona.id")
    LogBookDTO toLogBookDTO(LogBook logBook);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("nombre", "nombre");
        clavesToSort.put("descripcion", "descripcion");
        return clavesToSort;
    }

}
