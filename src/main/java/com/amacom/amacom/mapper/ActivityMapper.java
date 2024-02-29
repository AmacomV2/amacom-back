package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.ActivityDTO;
import com.amacom.amacom.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityMapper {

    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    Activity toActivity(ActivityDTO activityDTO);

    ActivityDTO toActivityDTO(Activity activity);
}
