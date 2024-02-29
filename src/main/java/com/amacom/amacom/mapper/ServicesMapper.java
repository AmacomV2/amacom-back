package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.ServicesDTO;
import com.amacom.amacom.model.Services;

@Mapper
public interface ServicesMapper {

    ServicesMapper INSTANCE = Mappers.getMapper(ServicesMapper.class);

    Services toServices(ServicesDTO servicesDTO);

    ServicesDTO toServicesDTO(Services services);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("name", "name");
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}
