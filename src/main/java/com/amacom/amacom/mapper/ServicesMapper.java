package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.ServicesDTO;
import com.amacom.amacom.model.Services;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServicesMapper {

    ServicesMapper INSTANCE = Mappers.getMapper(ServicesMapper.class);

    Services toServices(ServicesDTO servicesDTO);

    ServicesDTO toServicesDTO(Services services);

}
