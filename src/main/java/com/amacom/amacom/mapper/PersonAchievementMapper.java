package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.AchievementDTO;
import com.amacom.amacom.dto.PersonAchievementDTO;
import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.model.PersonAchievement;

@Mapper
public interface PersonAchievementMapper {

    PersonAchievementMapper INSTANCE = Mappers.getMapper(PersonAchievementMapper.class);

    PersonAchievement toPersonAchievement(PersonAchievementDTO personAchievementDTO);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "idAchievement", source = "achievement.id")
    PersonAchievementDTO toPersonAchievementDTO(PersonAchievement personAchievement);

    /**
     * Mapper used to map Subject data inside PersonAchievement.achievement
     *
     * @param achievement
     * @return
     */
    static AchievementDTO toAchievementDTO(Achievement achievement) {
        return AchievementMapper.INSTANCE.toAchievementDTO(achievement);
    }
}
