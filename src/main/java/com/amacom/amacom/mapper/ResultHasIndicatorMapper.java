package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.ResultHasIndicatorDTO;
import com.amacom.amacom.model.ResultHasIndicator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResultHasIndicatorMapper {

    ResultHasIndicatorMapper INSTANCE = Mappers.getMapper(ResultHasIndicatorMapper.class);

    ResultHasIndicator toResultHasIndicator(ResultHasIndicatorDTO resultHasIndicatorDTO);

    @Mapping(target = "idResult", source = "result.id")
    @Mapping(target = "idIndicator", source = "indicator.id")
    ResultHasIndicatorDTO toResultHasIndicatorDTO(ResultHasIndicator resultHasIndicator);
}
