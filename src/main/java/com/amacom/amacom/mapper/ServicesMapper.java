package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.ServicesDTO;
import com.amacom.amacom.model.Services;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface ServicesMapper {

    ServicesMapper INSTANCE = Mappers.getMapper(ServicesMapper.class);

    Services toServices(ServicesDTO servicesDTO);

    ServicesDTO toServicesDTO(Services services);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("name", "name");
        clavesToSort.put("createdAt", "createdAt");
        return clavesToSort;
    }

}
