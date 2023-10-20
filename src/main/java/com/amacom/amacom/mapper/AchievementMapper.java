package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.AchievementDTO;
import com.amacom.amacom.model.Achievement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AchievementMapper {

    AchievementMapper INSTANCE = Mappers.getMapper(AchievementMapper.class);

    Achievement toAchievement(AchievementDTO achievementDTO);

    @Mapping(target = "idSubject", source = "subject.id")
    @Mapping(target = "nombreSubject", source = "subject.nombre")
    AchievementDTO toAchievementDTO(Achievement achievement);
}
