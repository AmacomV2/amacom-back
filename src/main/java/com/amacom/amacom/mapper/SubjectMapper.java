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
    @Mapping(target = "idAssociatedResults", source = "associatedResults.id")
    SubjectDTO toSubjectDTO(Subject subject);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("name", "name");
        clavesToSort.put("createdAt", "createdAt");
        return clavesToSort;
    }

}
