package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.IndicatorDTO;
import com.amacom.amacom.model.Indicator;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IndicatorMapper {

    IndicatorMapper INSTANCE = Mappers.getMapper(IndicatorMapper.class);

    Indicator toIndicator(IndicatorDTO indicatorDTO);

    IndicatorDTO toIndicatorDTO(Indicator indicator);

}
