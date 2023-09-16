package com.amacom.amacom.mapper.auth;

import com.amacom.amacom.dto.auth.AuthResponseDTO;
import com.amacom.amacom.model.auth.AuthResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthResponseMapper {

    AuthResponseMapper INSTANCE = Mappers.getMapper(AuthResponseMapper.class);


    //DTO TO ENTITY
    AuthResponse toAuthResponse(AuthResponseDTO personaDTO);

    //ENTITY TO DTO

    AuthResponseDTO toAuthResponseDTO(AuthResponse persona);

}
