package com.amacom.amacom.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.auth.AuthResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.model.auth.AuthResponse;

@Mapper
public interface AuthResponseMapper {

    AuthResponseMapper INSTANCE = Mappers.getMapper(AuthResponseMapper.class);

    // DTO TO ENTITY
    AuthResponse toAuthResponse(AuthResponseDTO personDTO);

    // ENTITY TO DTO

    AuthResponseDTO toAuthResponseDTO(AuthResponse person);

    SuccessDTO toOkResponseDTO(AuthResponse personDTO);

}
