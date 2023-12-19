package com.amacom.amacom.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.auth.LoginRequestDTO;
import com.amacom.amacom.model.auth.LoginRequest;

@Mapper
public interface LoginRequestMapper {

    LoginRequestMapper INSTANCE = Mappers.getMapper(LoginRequestMapper.class);

    // DTO TO ENTITY

    LoginRequest toLoginRequest(LoginRequestDTO loginRequestDTO);

    // ENTITY TO DTO

    LoginRequestDTO toLoginRequestDTO(LoginRequest loginRequest);

}
