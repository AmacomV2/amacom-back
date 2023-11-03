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

    @Mapping(target = "nameDocumentType", source = "documentType.name")
    @Mapping(target = "nameGenero", source = "gender.name")
    @Mapping(target = "nameStatusCivil", source = "statusCivil.name")
    @Mapping(target = "idDocumentType", source = "documentType.id")
    @Mapping(target = "idGenero", source = "gender.id")
    @Mapping(target = "idStatusCivil", source = "statusCivil.id")
    PersonDTO toPersonDTO(Person person);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("name", "name");
        clavesToSort.put("documentNo", "documentNo");
        clavesToSort.put("nameDocumentType", "documentType.name");
        clavesToSort.put("nameGenero", "gender.name");
        clavesToSort.put("nameStatusCivil", "statusCivil.name");
        clavesToSort.put("birthDay", "birthDay");
        clavesToSort.put("createdAt", "createdAt");
        return clavesToSort;
    }
}
