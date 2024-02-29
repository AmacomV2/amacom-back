package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.FeelingsDTO;
import com.amacom.amacom.model.Feelings;

@Mapper
public interface FeelingsMapper {

    FeelingsMapper INSTANCE = Mappers.getMapper(FeelingsMapper.class);

    Feelings toFeelings(FeelingsDTO feelingsDTO);

    FeelingsDTO toFeelingsDTO(Feelings feelings);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("name", "name");
        keysToSort.put("description", "description");
        return keysToSort;
    }
}
