package com.amacom.amacom.mapper.auth;

import com.amacom.amacom.model.auth.RegisterRequest;
import com.amacom.amacom.dto.auth.RegisterRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterRequestMapper {

    RegisterRequestMapper INSTANCE = Mappers.getMapper(RegisterRequestMapper.class);

    //DTO TO ENTITY
    RegisterRequest toRegisterRequest(RegisterRequestDTO registerRequestDTO);

    //ENTITY TO DTO

    RegisterRequestDTO toRegisterRequestDTO(RegisterRequest registerRequest);


}
