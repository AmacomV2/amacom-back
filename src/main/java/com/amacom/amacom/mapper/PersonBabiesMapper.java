package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PersonBabiesDTO;
import com.amacom.amacom.model.PersonBabies;

@Mapper
public interface PersonBabiesMapper {

    PersonBabiesMapper INSTANCE = Mappers.getMapper(PersonBabiesMapper.class);

    PersonBabies toPersonBabies(PersonBabiesDTO personBabiesDTO);

    @Mapping(target = "idParent", source = "parent.id")
    @Mapping(target = "idChild", source = "child.id")
    PersonBabiesDTO toPersonBabiesDTO(PersonBabies personBabies);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}
