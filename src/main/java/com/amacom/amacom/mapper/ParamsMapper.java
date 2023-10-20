package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.ParamsDTO;
import com.amacom.amacom.model.Params;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParamsMapper {

    ParamsMapper INSTANCE = Mappers.getMapper(ParamsMapper.class);

    Params toParams(ParamsDTO paramsDTO);

    ParamsDTO toParamsDTO(Params params);
}
