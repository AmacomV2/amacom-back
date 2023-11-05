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

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("name", "name");
        clavesToSort.put("documentNo", "documentNo");
        clavesToSort.put("documentType", "documentType");
        clavesToSort.put("documentTypeId", "documentType.id");
        clavesToSort.put("gender", "gender");
        clavesToSort.put("genderId", "gender.id");
        clavesToSort.put("civilStatus", "civilStatus");
        clavesToSort.put("civilStatusId", "civilStatus.id");
        clavesToSort.put("birthDay", "birthDay");
        clavesToSort.put("createdAt", "createdAt");
        return clavesToSort;
    }
}
