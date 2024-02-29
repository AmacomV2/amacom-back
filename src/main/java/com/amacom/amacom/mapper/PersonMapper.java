package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PersonDTO;
import com.amacom.amacom.model.Person;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    // DTO TO ENTITY

    Person toPerson(PersonDTO personDTO);

    // ENTITY TO DTO

    @Mapping(target = "documentType", source = "documentType")
    @Mapping(target = "documentTypeId", source = "documentType.id")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "genderId", source = "gender.id")
    @Mapping(target = "civilStatus", source = "civilStatus")
    @Mapping(target = "civilStatusId", source = "civilStatus.id")
    PersonDTO toPersonDTO(Person person);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("name", "name");
        keysToSort.put("documentNo", "documentNo");
        keysToSort.put("documentType", "documentType");
        keysToSort.put("documentTypeId", "documentType.id");
        keysToSort.put("gender", "gender");
        keysToSort.put("genderId", "gender.id");
        keysToSort.put("civilStatus", "civilStatus");
        keysToSort.put("civilStatusId", "civilStatus.id");
        keysToSort.put("birthDate", "birthDate");
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }
}
