package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PersonAchievementDTO;
import com.amacom.amacom.model.PersonAchievement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonAchievementMapper {

    PersonAchievementMapper INSTANCE = Mappers.getMapper(PersonAchievementMapper.class);

    PersonAchievement toPersonAchievement(PersonAchievementDTO personAchievementDTO);

    @Mapping(target = "idPersona", source = "persona.id")
    @Mapping(target = "idAchievement", source = "achievement.id")
    PersonAchievementDTO toPersonAchievementDTO(PersonAchievement personAchievement);

}
