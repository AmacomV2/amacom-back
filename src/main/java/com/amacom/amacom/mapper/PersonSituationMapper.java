package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.model.PersonSituation;

@Mapper
public interface PersonSituationMapper {

    PersonSituationMapper INSTANCE = Mappers.getMapper(PersonSituationMapper.class);

    PersonSituation toPersonSituation(PersonSituationDTO personSituationDTO);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "createdById", source = "createdBy.id")
    @Mapping(target = "currentDiagnosis", source = "currentDiagnosis")
    @Mapping(target = "affectationDegree", source = "affectationDegree")
    @Mapping(target = "subjectId", source = "subject.id")
    PersonSituationDTO toPersonSituationDTO(PersonSituation personSituation);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("behavior", "behavior");
        clavesToSort.put("affectationDegree", "affectationDegree");
        clavesToSort.put("fistThought", "fistThought");
        clavesToSort.put("description", "description");
        return clavesToSort;
    }
}
