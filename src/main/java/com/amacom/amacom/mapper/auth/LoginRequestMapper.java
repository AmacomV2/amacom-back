package com.amacom.amacom.mapper.auth;

import com.amacom.amacom.model.auth.LoginRequest;
import com.amacom.amacom.dto.auth.LoginRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginRequestMapper {

    LoginRequestMapper INSTANCE = Mappers.getMapper(LoginRequestMapper.class);

    //DTO TO ENTITY

    LoginRequest toLoginRequest(LoginRequestDTO loginRequestDTO);

    //ENTITY TO DTO

    LoginRequestDTO toLoginRequestDTO(LoginRequest loginRequest);


}
