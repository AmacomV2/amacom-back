package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.model.PersonSituation;
import org.mapstruct.Mapping;

public class PersonSituationMapperVar {

    public static PersonSituationMapperVar INSTANCE = new PersonSituationMapperVar();

    public PersonSituationDTO toPersonSituationDTO(PersonSituation personSituation) {

        var personSituationDto = PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituation);
        personSituationDto
                .setCurrentDiagnosis(DiagnosisMapper.INSTANCE.toDiagnosisDTO(personSituation.getCurrentDiagnosis()));
        return personSituationDto;

    }
}
