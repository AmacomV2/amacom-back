package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.ResultHasIndicatorDTO;
import com.amacom.amacom.model.ResultHasIndicator;

@Mapper
public interface ResultHasIndicatorMapper {

    ResultHasIndicatorMapper INSTANCE = Mappers.getMapper(ResultHasIndicatorMapper.class);

    ResultHasIndicator toResultHasIndicator(ResultHasIndicatorDTO resultHasIndicatorDTO);

    @Mapping(target = "idResult", source = "result.id")
    @Mapping(target = "idIndicator", source = "indicator.id")
    @Mapping(target = "indicatorName", source = "indicator.name")
    @Mapping(target = "indicatorDescription", source = "indicator.description")
    ResultHasIndicatorDTO toResultHasIndicatorDTO(ResultHasIndicator resultHasIndicator);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }
}
