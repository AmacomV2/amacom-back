package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.FeelingsDTO;
import com.amacom.amacom.model.Feelings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeelingsMapper {

    FeelingsMapper INSTANCE = Mappers.getMapper(FeelingsMapper.class);

    Feelings toFeelings(FeelingsDTO feelingsDTO);

    FeelingsDTO toFeelingsDTO(Feelings feelings);
}
