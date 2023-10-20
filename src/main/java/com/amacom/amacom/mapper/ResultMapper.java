package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.ResultDTO;
import com.amacom.amacom.model.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResultMapper {

    ResultMapper INSTANCE = Mappers.getMapper(ResultMapper.class);

    Result toResult(ResultDTO resultDTO);

    @Mapping(target = "idDiagnosis", source = "diagnosis.id")
    ResultDTO toResultDTO(Result result);

}
