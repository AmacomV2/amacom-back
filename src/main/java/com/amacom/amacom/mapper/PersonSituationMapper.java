package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.amacom.amacom.model.Feelings;
import com.amacom.amacom.model.PersonSituationHasFeelings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.model.PersonSituation;

@Mapper
public interface PersonSituationMapper {

    PersonSituationMapper INSTANCE = Mappers.getMapper(PersonSituationMapper.class);

    @Mapping(target = "feelings", ignore = true)
    //@Mapping(target = "feelings.feelings.id", source = "feelings")
    //@Mapping(target = "feelings.personSituation.id", source = "id")
    PersonSituation toPersonSituation(PersonSituationDTO personSituationDTO);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "createdById", source = "createdBy.id")
    @Mapping(target = "currentDiagnosis", source = "currentDiagnosis")
    @Mapping(target = "affectationDegree", source = "affectationDegree")
    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "subjectName", source = "subject.name")
    PersonSituationDTO toPersonSituationDTO(PersonSituation personSituation);

    static List<UUID> toFeelings(List<PersonSituationHasFeelings> feelingsList){
        return feelingsList.stream().map((val)->{
            return val.getFeelings().getId();
        }).collect(Collectors.toList());
    }
    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("behavior", "behavior");
        keysToSort.put("affectationDegree", "affectationDegree");
        keysToSort.put("fistThought", "fistThought");
        keysToSort.put("description", "description");
        return keysToSort;
    }
}
