package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.ResultDTO;
import com.amacom.amacom.model.Result;

@Mapper
public interface ResultMapper {

    ResultMapper INSTANCE = Mappers.getMapper(ResultMapper.class);

    Result toResult(ResultDTO resultDTO);

    @Mapping(target = "diagnosisId", source = "diagnosis.id")
    ResultDTO toResultDTO(Result result);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}
