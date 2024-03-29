package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.SubjectDTO;
import com.amacom.amacom.model.Subject;

@Mapper
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    Subject toSubject(SubjectDTO subjectDTO);

    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "associatedResultId", source = "associatedResult.id")
    SubjectDTO toSubjectDTO(Subject subject);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("name", "name");
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}
